package com.convector.david_000.convector_valute

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.convector.david_000.convector_valute.model.CurrenciesState
import com.convector.david_000.convector_valute.model.СurrencyAction
import com.convector.david_000.convector_valute.network.repository.CurrenciesRepository
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
    private val currenciesRepository: CurrenciesRepository
) : ViewModel() {


    private val _actions = EventSharedFlow<СurrencyAction>()
    val actions = _actions.asSharedFlow()

    private val _state = MutableStateFlow<CurrenciesState>(CurrenciesState.Loading)
    val state = _state.asStateFlow()

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            //_state.emit(CurrenciesState.Content(currenciesRepository.getCurrencies()))
        }
    }

    fun getSymbols() {
        viewModelScope.launch(Dispatchers.IO) {
            val symbols = mutableListOf<Pair<String,String>>()
            currenciesRepository.getSymbols().forEach {
                symbols.add(Pair(it.cur,it.value))
            }
            _state.emit(CurrenciesState.Symbols(symbols))
        }
    }

}