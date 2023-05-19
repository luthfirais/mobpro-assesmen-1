package org.d3if0132.kalkulatorpersegipanjang.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PpEntity::class], version = 1, exportSchema = false)
abstract class PpDb : RoomDatabase() {

    abstract val dao: PpDao

    companion object {

        @Volatile
        private var INSTANCE: PpDb? = null

        fun getInstance(context: Context): PpDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PpDb::class.java,
                        "pp.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}