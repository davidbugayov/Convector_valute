package com.convector.david_000.convector_valute.network.responce

data class QueryDto(
    val amount: Double,
    val from: String,
    val to: String
)