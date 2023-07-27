package com.convector.david_000.convector_valute.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class SymbolsItem(
@PrimaryKey(autoGenerate = true)
val id: Int = 0,
    val cur: String,
    val value: String
    // @Exposed
  //  val symbols: Map<String, String>
    )