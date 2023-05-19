package org.d3if0132.kalkulatorpersegipanjang.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0132.kalkulatorpersegipanjang.db.PpDao

class HistoryViewModel(private val db: PpDao) : ViewModel() {
    val data = db.getAllPp()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}