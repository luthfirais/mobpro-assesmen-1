package org.d3if0132.kalkulatorpersegipanjang.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if0132.kalkulatorpersegipanjang.R
import org.d3if0132.kalkulatorpersegipanjang.databinding.FragmentBentukBinding
import org.d3if0132.kalkulatorpersegipanjang.model.KategoriPp

class BentukFragment : Fragment() {

    private lateinit var binding: FragmentBentukBinding
    private val args: BentukFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBentukBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun UpdateUI(kategori: KategoriPp) {
        val actionBar = (requireActivity() as AppCompatActivity)
        when (kategori) {
            KategoriPp.KECIL -> {
                actionBar?.title = getString(R.string.judul_kecil)

                binding.textView.text = getString(R.string.bentuk_kecil)
            }
            KategoriPp.SEDANG -> {
                actionBar?.title = getString(R.string.judul_sedang)

                binding.textView.text = getString(R.string.bentuk_sedang)
            }
            KategoriPp.BESAR -> {
                actionBar?.title = getString(R.string.judul_besar)

                binding.textView.text = getString(R.string.bentuk_besar)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        UpdateUI(args.kategori)
    }

}