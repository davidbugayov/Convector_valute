package com.convector.david_000.convector_valute.data

data class TrainItem (
    val stationNameFrom: String,
    val station0: String,
    val stationTo: String,
    val dateStart: String,
    val fromTime: String,
    val codeFrom: Long,
    val codeTo: Long,
    val toTime: String,
    val timeInWay: String,
    val trainNum: String,
    val list: List<CarsItem>,
)