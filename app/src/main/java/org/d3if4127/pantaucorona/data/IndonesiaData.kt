package org.d3if4127.pantaucorona.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "corona_table")
data class IndonesiaData(
    @PrimaryKey @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "positif")
    val positif: String,

    @ColumnInfo(name = "meninggal")
    val meninggal: String
)