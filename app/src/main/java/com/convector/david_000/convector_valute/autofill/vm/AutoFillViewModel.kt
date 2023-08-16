package com.convector.david_000.convector_valute.autofill.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.convector.david_000.convector_valute.core.BaseViewModel
import com.convector.david_000.convector_valute.model.RZDAction
import com.convector.david_000.convector_valute.model.RZDState
import com.convector.david_000.convector_valute.network.repository.RZDRepository
import com.convector.david_000.convector_valute.utils.EventSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class AutoFillViewModel @Inject constructor(
    private val rzdRepository: RZDRepository
) : BaseViewModel() {


    private val _actions = EventSharedFlow<AutoFillAction>()
    val actions = _actions.asSharedFlow()

    private val _state = MutableStateFlow<AutoFillState>(AutoFillState.Loading)
    val state = _state.asStateFlow()

    fun getSuggestedStation(stationName: String) {
        safeLaunch(
            coroutineContext =  Dispatchers.IO,
            {
                Timber.e(it)
            }
            _state.emit(AutoFillState.Loading)
            val suggestStation = rzdRepository.stations(stationName)
            _state.emit(AutoFillState.Content(suggestStation))
        )
    }

}