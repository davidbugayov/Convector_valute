package com.convector.david_000.convector_valute.data.remote.responce

data class TimesheetDto(
    val result: String?,
    val RID: Long,
    val tp: List<TpItemDto>,
    val timestamp: String?
)
