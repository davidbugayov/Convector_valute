package com.convector.david_000.convector_valute.model

import com.convector.david_000.convector_valute.network.responce.ConvertDto

sealed class CurrenciesState {
    object Loading : CurrenciesState()

    data class Content(
        val convertDto: ConvertDto
    ) : CurrenciesState()


}
