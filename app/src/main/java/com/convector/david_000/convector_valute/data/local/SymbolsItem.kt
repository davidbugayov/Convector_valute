package com.convector.david_000.convector_valute.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SymbolsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
   // @Exposed
    val symbols: Map<String, String>?
    )