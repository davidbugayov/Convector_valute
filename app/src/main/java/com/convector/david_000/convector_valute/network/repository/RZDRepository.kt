package com.convector.david_000.convector_valute.network.repository

import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.data.local.RZDDatabase
import com.convector.david_000.convector_valute.data.local.Stations
import com.convector.david_000.convector_valute.data.remote.responce.TicketsDto
import com.convector.david_000.convector_valute.network.RZDApiRetrofit
import javax.inject.Inject
import timber.log.Timber

class RZDRepository @Inject constructor(
    private val apiRetrofit: RZDApiRetrofit,
    private val database: RZDDatabase
) {

    suspend fun stations(stationNamePart: String): List<StationItem> {
        //from db
        val localStation = database.RZDDao.getStationsByName(stationNamePart)
        if (localStation.isNotEmpty()) {
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
            stations.map {
                StationItem(it.stationName, it.stationCode)
            }
        } else {
            emptyList()
        }
    }

    suspend fun tickets(): TicketsDto? {
        val symbolsRest = apiRetrofit.timetable()
        return if (symbolsRest.isSuccessful && symbolsRest.body() != null
        ) {
            var body = symbolsRest.body()
            var count = 1
            var result = body?.result
            var rid = body?.RID
            do {
                body = apiRetrofit.timetable(rid = rid).body()
                count++
                result = body?.result
            } while (result == "RID")
            Timber.e("ГОВНО   $count")
            body
        } else {
            null
        }
    }

}