package com.convector.david_000.convector_valute.model

import com.convector.david_000.convector_valute.data.StationItem

sealed class RZDState {
    object Loading : RZDState()

    data class Stations(
        val stations: List<StationItem>
    ) : RZDState()
}
