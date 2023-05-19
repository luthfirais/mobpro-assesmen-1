package org.d3if0132.kalkulatorpersegipanjang.model

import org.d3if0132.kalkulatorpersegipanjang.db.PpEntity

fun PpEntity.hitungPp(): HasilPp {
    val panjang = panjang
    val lebar = lebar
    var hasil = 0f
    val kategori = if (isLuas) {
        val luas = panjang.toFloat() * lebar.toFloat()
        hasil = luas
        when {

            hasil < 50 -> KategoriPp.KECIL
            hasil > 200 -> KategoriPp.BESAR
            else -> KategoriPp.SEDANG
        }
    } else {
        val keliling = panjang.toFloat() + lebar.toFloat()
        hasil = keliling
        when {
            hasil < 50 -> KategoriPp.KECIL
            hasil > 200 -> KategoriPp.BESAR
            else -> KategoriPp.SEDANG
        }
    }
    return HasilPp(hasil, kategori)
}