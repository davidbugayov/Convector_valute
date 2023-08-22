package com.convector.david_000.convector_valute.train.vm

import com.convector.david_000.convector_valute.data.TrainItem

sealed class TrainsState {
    object Loading : TrainsState()

    data class Content(val trainList: List<TrainItem>) : TrainsState()

    object Empty : TrainsState()

    object Error : TrainsState()
}
