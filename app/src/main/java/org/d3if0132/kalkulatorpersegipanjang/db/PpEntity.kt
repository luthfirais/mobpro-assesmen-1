package org.d3if0132.kalkulatorpersegipanjang.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pp")
data class PpEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var panjang: Float,
    var lebar: Float,
    var isLuas: Boolean
)

