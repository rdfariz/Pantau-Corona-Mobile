package org.d3if4127.pantaucorona.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if4127.pantaucorona.data.IndonesiaData

@Database(entities = [IndonesiaData::class], version = 1, exportSchema = false)
abstract class CoronaDB : RoomDatabase() {

    abstract val coronaDAO: CoronaDAO

    companion object {
        @Volatile
        private var INSTANCE: CoronaDB? = null

        fun getInstance(context: Context): CoronaDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoronaDB::class.java,
                        "corona_table"
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