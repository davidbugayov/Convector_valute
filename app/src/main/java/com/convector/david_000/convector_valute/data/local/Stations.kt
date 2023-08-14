package com.convector.david_000.convector_valute.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Stations")
data class Stations(
    val stationName: String,
    @PrimaryKey
    val stationCode: Long,
    val S: Int,
    val L: Int,
)