package com.convector.david_000.convector_valute.network.repository

import com.convector.david_000.convector_valute.data.local.CurrenciesDatabase
import com.convector.david_000.convector_valute.data.local.SymbolsItem
import com.convector.david_000.convector_valute.network.CurrencyApiRetrofit
import com.convector.david_000.convector_valute.data.remote.responce.ConvertDto
import com.convector.david_000.convector_valute.data.remote.responce.SymbolsDto
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

    suspend fun getSymbols(): SymbolsItem? {
        return if(currencyApiRetrofit.symbols().isSuccessful && currencyApiRetrofit.symbols().body()!= null){
            val symbolsItem = SymbolsItem(symbols = currencyApiRetrofit.symbols().body()!!.symbols)
            currenciesDatabase.currenciesDao.updateCurrencies(symbolsItem)
            symbolsItem
        }else{
            currenciesDatabase.currenciesDao.getCurrencies()
        }
    }
}