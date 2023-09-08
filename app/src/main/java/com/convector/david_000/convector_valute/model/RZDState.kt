package com.convector.david_000.convector_valute.model

import com.convector.david_000.convector_valute.data.HeaderItem
import com.convector.david_000.convector_valute.data.StationItem

sealed class RZDState {
    data object Loading : RZDState()

    data class Content(
        val header: HeaderItem,
        val stations: List<StationItem>
    ) : RZDState()
}
