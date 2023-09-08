package com.convector.david_000.convector_valute.data.remote.responce

data class TpItemDto(
    val from: String,
    val fromCode: Int,
    val where: String,
    val whereCode: Long,
    val date: String,
    val noSeats: Boolean,
    val defShowTime: String,
    val state: String,
    val list: List<TrainItemDto>
)
