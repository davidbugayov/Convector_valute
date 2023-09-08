package com.convector.david_000.convector_valute.train.vm

import com.convector.david_000.convector_valute.core.BaseViewModel
import com.convector.david_000.convector_valute.data.HeaderItem
import com.convector.david_000.convector_valute.data.TrainItem
import com.convector.david_000.convector_valute.network.repository.RZDRepository
import com.convector.david_000.convector_valute.utils.EventSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class TrainsViewModel @Inject constructor(
    private val rzdRepository: RZDRepository
) : BaseViewModel() {

    private val _actions = EventSharedFlow<TrainsAction>()
    val actions = _actions.asSharedFlow()

    private val _state = MutableStateFlow<TrainsState>(TrainsState.Loading)
    val state = _state.asStateFlow()

    fun timetable() {
        safeLaunch(onFailure = { _state.emit(TrainsState.Error) }) {
            _state.emit(TrainsState.Loading)
            val timeTable = rzdRepository.tickets()
            val trainList = timeTable[0].tpSheetItem.list.map {
                TrainItem(
                    routeFrom = it.route0,
                    station0 = it.station0,
                    stationTo = it.station1,
                    fromTime = it.time0,
                    toTime = it.time1,
                    timeInWay = it.timeInWay,
                    list = listOf()
                )
            }
            _state.emit(
                TrainsState.Content(
                    header = HeaderItem(
                        data = timeTable[0].tpSheetItem.date,
                        fromStation = timeTable[0].tpSheetItem.from,
                        toStation = timeTable[0].tpSheetItem.where
                    ), trainList = trainList
                )
            )
        }
    }
}