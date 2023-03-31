package org.d3if0132.kalkulatorpersegipanjang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if0132.kalkulatorpersegipanjang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { hitungPersegiPanjang() }
    }

    private fun hitungPersegiPanjang() {
        val panjang = binding.panjangInp.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(this, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val lebar = binding.lebarInp.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(this, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.hitung_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val luas = panjang.toInt() * lebar.toInt()
        val keliling = panjang.toInt() + lebar.toInt()

        if (selectedId == R.id.luasRadioButton) {
            binding.hitungTextView.text = getString(R.string.hitung_x, luas)
        } else {
            binding.hitungTextView.text = getString(R.string.hitung_x, keliling)
        }
    }
}