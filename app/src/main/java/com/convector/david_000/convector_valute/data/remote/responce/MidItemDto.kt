package com.convector.david_000.convector_valute.data.remote.responce

data class MidItemDto(
    val midCode: Int,
    val midPt: String,
    val transTime: Int,
    val timeBetweenCurCase: Int,
    val totalTravelTime: Int,
    val cases: List<List<CaseDto>>
)