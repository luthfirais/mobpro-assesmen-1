package org.d3if0132.kalkulatorpersegipanjang.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PpDao {

    @Insert
    fun insert(Pp: PpEntity)

    @Query("SELECT * FROM pp ORDER BY id DESC LIMIT 1")
    fun getLastPp(): LiveData<PpEntity>

    @Query("SELECT * FROM pp")
    fun getAllPp(): LiveData<List<PpEntity>>

    @Query("DELETE FROM pp")
    fun clearData()
}
