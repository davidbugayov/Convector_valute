package com.convector.david_000.convector_valute.network.repository

import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.data.local.RZDDatabase
import com.convector.david_000.convector_valute.data.local.Stations
import com.convector.david_000.convector_valute.data.remote.responce.TicketsDto
import com.convector.david_000.convector_valute.network.RZDApiRetrofit
import javax.inject.Inject

class RZDRepository @Inject constructor(
    private val apiRetrofit: RZDApiRetrofit,
    private val database: RZDDatabase
) {

    suspend fun stations(stationNamePart: String): List<StationItem> {
        //from db
        val localStation = database.RZDDao.getStationsByName(stationNamePart)
        if(localStation.isNotEmpty()){
            return localStation.map {
                StationItem(it.stationName, it.stationCode)
            }
        }

        //from remote
        val stationsSuggest = apiRetrofit.stations(stationNamePart)
        return if (stationsSuggest.isSuccessful && stationsSuggest.body() != null) {
            val stations = stationsSuggest.body()!!.map {
                Stations(it.n, it.c, it.S, it.L)
            }
            database.RZDDao.insertStations(stations = stations)
            database.RZDDao.getStationsByName(stationNamePart).map {
                StationItem(it.stationName, it.stationCode)
            }
        } else {
            emptyList()
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