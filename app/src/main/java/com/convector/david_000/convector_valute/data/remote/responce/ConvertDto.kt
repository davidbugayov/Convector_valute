package com.convector.david_000.convector_valute.data.remote.responce

import java.util.Date

data class ConvertDto(
    val date: Date,
    val info: InfoDto,
    val query: QueryDto,
    val result: Double,
    val success: Boolean
)