package com.convector.david_000.convector_valute

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.convector.david_000.convector_valute.model.RZDAction
import com.convector.david_000.convector_valute.model.RZDState
import com.convector.david_000.convector_valute.network.repository.RZDRepository
import com.convector.david_000.convector_valute.utils.EventSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val rzdRepository: RZDRepository
) : ViewModel() {


    private val _actions = EventSharedFlow<RZDAction>()
    val actions = _actions.asSharedFlow()

    private val _state = MutableStateFlow<RZDState>(RZDState.Loading)
    val state = _state.asStateFlow()

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            //_state.emit(CurrenciesState.Content(currenciesRepository.getCurrencies()))
        }
    }

    fun getSuggestedStation(stationName: String) {
        viewModelScope.launch(Dispatchers.IO) {
           val suggestStation = rzdRepository.stations(stationName)
            _state.emit(RZDState.Stations(suggestStation))
        }
    }

}