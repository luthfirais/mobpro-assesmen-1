package org.d3if0132.kalkulatorpersegipanjang.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0132.kalkulatorpersegipanjang.db.PpDao
import org.d3if0132.kalkulatorpersegipanjang.db.PpEntity
import org.d3if0132.kalkulatorpersegipanjang.model.HasilPp
import org.d3if0132.kalkulatorpersegipanjang.model.KategoriPp
import org.d3if0132.kalkulatorpersegipanjang.model.hitungPp

class HitungViewModel(private val db: PpDao) : ViewModel() {

    private val hasilPp = MutableLiveData<HasilPp?>()
    private val navigasi = MutableLiveData<KategoriPp>()

    fun hitungPp(panjang: Float, lebar: Float, isLuas: Boolean) {
        val dataPp = PpEntity(
            panjang = panjang,
            lebar = lebar,
            isLuas = isLuas
        )
        hasilPp.value = dataPp.hitungPp()


        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataPp)
            }
        }
    }

    fun getHasilPp(): LiveData<HasilPp?> = hasilPp

    fun mulaiNavigasi() {
        navigasi.value = hasilPp.value?.kategori
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi(): LiveData<KategoriPp> = navigasi
}