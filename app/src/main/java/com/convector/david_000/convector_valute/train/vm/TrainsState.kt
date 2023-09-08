package com.convector.david_000.convector_valute.train.vm

import com.convector.david_000.convector_valute.data.HeaderItem
import com.convector.david_000.convector_valute.data.TrainItem

sealed class TrainsState {
    data object Loading : TrainsState()

    data class Content(
        val header: HeaderItem,
        val trainList: List<TrainItem>
    ) : TrainsState()

    data object Empty : TrainsState()

    data object Error : TrainsState()
}
