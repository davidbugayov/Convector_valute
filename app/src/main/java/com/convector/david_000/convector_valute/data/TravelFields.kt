package com.convector.david_000.convector_valute.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TravelFields(
    var stationFromItem: StationItem? = null,
    var stationToItem: StationItem? = null,
    var dateTravel: Long? = null
) : Parcelable {
    // fun isValid() = stationFromItem != null && stationToItem!= null && dateTravel!= null
    fun isValid() = true
}
