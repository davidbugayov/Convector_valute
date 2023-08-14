package com.convector.david_000.convector_valute.network.repository

import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.data.local.RZDDatabase
import com.convector.david_000.convector_valute.data.remote.responce.TicketsDto
import com.convector.david_000.convector_valute.network.RZDApiRetrofit
import javax.inject.Inject

class RZDRepository @Inject constructor(
    private val apiRetrofit: RZDApiRetrofit,
    private val database: RZDDatabase
) {

//    suspend fun convert(amount: Double, from: String, to: String): ConvertDto {
//        return currencyApiRetrofit.convert(amount = amount, from = from, to = to, date = null)
//            .handle {
//                return@handle it
//            }
//    }

    suspend fun stations(stationNamePart: String): List<StationItem> {
        val stationsSuggest = apiRetrofit.stations(stationNamePart)
        val stationList = arrayListOf<StationItem>()
        return if (stationsSuggest.isSuccessful && stationsSuggest.body() != null) {
            stationsSuggest.body()!!.forEach {
                stationList.add(StationItem(it.n,it.c))
            }
            stationList
        } else {
            stationList
        }
    }

    suspend fun tickets(): TicketsDto? {
//        val currenciesCache = currenciesDatabase.currenciesDao.getCurrencies()
//        return if (currenciesCache.isNotEmpty()) {
//            currenciesCache
//        } else {
        val symbolsRest = apiRetrofit.tickets()
        return if (symbolsRest.isSuccessful && symbolsRest.body() != null
        ) {
            symbolsRest.body()

        } else {
            null
        }
    }

}