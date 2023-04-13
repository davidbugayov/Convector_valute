package com.convector.david_000.convector_valute.model

import com.convector.david_000.convector_valute.data.remote.responce.ConvertDto
import com.convector.david_000.convector_valute.data.remote.responce.SymbolsDto

sealed class CurrenciesState {
    object Loading : CurrenciesState()

    data class Symbols(
        val symbols: Map<String,String>?
    ) : CurrenciesState()


}
