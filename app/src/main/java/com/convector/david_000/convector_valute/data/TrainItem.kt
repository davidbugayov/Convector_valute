package com.convector.david_000.convector_valute.data

data class TrainItem (
    val routeFrom: String,
    val station0: String,
    val stationTo: String,
    val fromTime: String,
    val toTime: String,
    val timeInWay: String,
    val list: List<CarsItem>
)