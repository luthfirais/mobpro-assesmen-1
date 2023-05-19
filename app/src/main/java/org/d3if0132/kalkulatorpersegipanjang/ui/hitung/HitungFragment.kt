package org.d3if0132.kalkulatorpersegipanjang.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0132.kalkulatorpersegipanjang.R
import org.d3if0132.kalkulatorpersegipanjang.databinding.FragmentHitungBinding
import org.d3if0132.kalkulatorpersegipanjang.db.PpDb
import org.d3if0132.kalkulatorpersegipanjang.model.HasilPp
import org.d3if0132.kalkulatorpersegipanjang.model.KategoriPp


class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = PpDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate (R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate (R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungPp() }
        binding.bentukButton.setOnClickListener { viewModel.mulaiNavigasi() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilPp().observe(requireActivity()) { showResult(it) }
        viewModel.getNavigasi().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            findNavController().navigate(
                HitungFragmentDirections.actionHitungFragmentToBentukFragment(
                    it
                )
            )
            viewModel.selesaiNavigasi()
        }
    }

    private fun shareData() {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val tipe = if (selectedId == R.id.luasRadioButton)
            getString(R.string.luas)
        else
            getString(R.string.keliling)
        val message = getString(
            R.string.bagikan_template,
            binding.panjangEditText.text,
            binding.lebarEditText.text,
            tipe,
            binding.ppTextView.text,
            binding.kategoriTextView.text
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun hitungPp() {
        val panjang = binding.panjangEditText.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val lebar = binding.lebarEditText.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, R.string.hitung_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungPp(
            panjang.toFloat(),
            lebar.toFloat(),
            selectedId == R.id.luasRadioButton
        )
    }

    private fun getKategoriLabel(kategori: KategoriPp): String {
        val stringRes = when (kategori) {
            KategoriPp.KECIL -> R.string.kecil
            KategoriPp.SEDANG -> R.string.sedang
            KategoriPp.BESAR -> R.string.besar
        }
        return getString(stringRes)
    }

    private fun showResult(result: HasilPp?) {
        if (result == null) return
        binding.ppTextView.text = getString(R.string.pp_x, result.pp.toString())
        binding.kategoriTextView.text = getString(R.string.kategori_x,
            getKategoriLabel(result.kategori))
        binding.buttonGroup.visibility = View.VISIBLE
    }
}