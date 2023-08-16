package com.convector.david_000.convector_valute.autofill.vm

import com.convector.david_000.convector_valute.data.StationItem

sealed class AutoFillState {
    object Loading : AutoFillState()

    data class Content(val stationList: List<StationItem>) : AutoFillState()

    object Empty : AutoFillState()

    object Error : AutoFillState()
}
