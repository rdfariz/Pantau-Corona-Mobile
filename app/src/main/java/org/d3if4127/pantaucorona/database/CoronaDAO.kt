package org.d3if4127.pantaucorona.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.d3if4127.pantaucorona.data.IndonesiaData

@Dao
interface CoronaDAO {
    @Query("SELECT * FROM corona_table")
    fun getIndonesiaData(): LiveData<List<IndonesiaData>>

    @Query("DELETE FROM corona_table")
    fun deleteAllData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIndonesia(indonesiaData: List<IndonesiaData>)
}