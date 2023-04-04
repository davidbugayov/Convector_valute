package com.convector.david_000.convector_valute.network.repository

import com.convector.david_000.convector_valute.network.CurrencyApiRetrofit
import com.convector.david_000.convector_valute.network.responce.ConvertDto
import com.convector.david_000.convector_valute.utils.handle
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
    private val currencyApiRetrofit: CurrencyApiRetrofit
) {

    suspend fun getCurrencies(): ConvertDto {
        return currencyApiRetrofit.convert(amount = 5.3424, from = "EUR", to = "RUB", date = null)
            .handle {
                return@handle it
            }
    }
}