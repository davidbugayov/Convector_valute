package com.convector.david_000.convector_valute.data.remote.responce

data class CarItemDto(
    val carDataType: Int,
    val itype: Int,
    val type: String,
    val typeLoc: String,
    val freeSeats: Int,
    val pt: Int,
    val tariff: Long,
    val servCls: String,
    val disabledPerson: Boolean = false
)