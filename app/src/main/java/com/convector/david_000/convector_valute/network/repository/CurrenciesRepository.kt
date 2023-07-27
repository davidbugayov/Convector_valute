package com.convector.david_000.convector_valute.network.repository

import com.convector.david_000.convector_valute.data.local.CurrenciesDatabase
import com.convector.david_000.convector_valute.data.local.SymbolsItem
import com.convector.david_000.convector_valute.network.CurrencyApiRetrofit
import com.convector.david_000.convector_valute.data.remote.responce.ConvertDto
import com.convector.david_000.convector_valute.utils.handle
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
    private val currencyApiRetrofit: CurrencyApiRetrofit,
    private val currenciesDatabase: CurrenciesDatabase
) {

    suspend fun convert(amount: Double, from: String, to: String): ConvertDto {
        return currencyApiRetrofit.convert(amount = amount, from = from, to = to, date = null)
            .handle {
                return@handle it
            }
    }

    suspend fun getSymbols(): List<SymbolsItem> {
        val currenciesCache = currenciesDatabase.currenciesDao.getCurrencies()
        return if (currenciesCache.isNotEmpty()) {
            currenciesCache
        } else {
            val symbolsRest = currencyApiRetrofit.symbols()
            if (symbolsRest.isSuccessful && symbolsRest.body() != null
            ) {
                val listSymbols = mutableListOf<SymbolsItem>()
                symbolsRest.body()!!.symbols.keys.forEach { key ->
                    listSymbols.add(
                        SymbolsItem(
                            cur = key,
                            value = symbolsRest.body()!!.symbols[key]!!
                        )
                    )
                }
                currenciesDatabase.currenciesDao.insertCurrencies(listSymbols)
                listSymbols
            } else {
                emptyList()
            }
        }
    }

}