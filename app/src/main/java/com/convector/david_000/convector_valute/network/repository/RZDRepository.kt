package com.convector.david_000.convector_valute.network.repository

import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.data.local.RZDDatabase
import com.convector.david_000.convector_valute.data.local.Stations
import com.convector.david_000.convector_valute.data.local.TPSheet
import com.convector.david_000.convector_valute.data.remote.responce.TimesheetDto
import com.convector.david_000.convector_valute.network.RZDApiRetrofit
import com.convector.david_000.convector_valute.network.interceptor.CookieApiInterceptor
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

    suspend fun tickets(): List<TPSheet> {
        val tpList = database.RZDDao.getTimesheet()
       // return if (tpList.isEmpty()) {
        return if (true) {
            val symbolsRest = apiRetrofit.timetable()
            return if (symbolsRest.isSuccessful && symbolsRest.body() != null) {
                var body = symbolsRest.body()
                var count = 1
                var result: String?
                do {
                    body = apiRetrofit.checkRID(rid = body?.RID!!).body()
                    count++
                    result = body?.result
                } while (result == "RID")
                Timber.e("count $count")
                val tpListRemote = body!!.tp.map {
                    TPSheet(
                        tpSheetItem = it,
                    )
                }
//                tpListRemote.forEach {
//                    database.RZDDao.insertTimetable(it)
//                }
                tpListRemote
            } else {
                emptyList()
            }
        } else {
            tpList
        }
    }

    suspend fun carriage(
        trainNum: String,
        codeFrom: Long,
        codeTo: Long,
        startDate: String,
        startTime: String
    ): TimesheetDto? {

        val carriage = apiRetrofit.carriage(
            tnum0 = trainNum,
            code0 = codeFrom,
            code1 = codeTo,
            dt0 = startDate,
            time0 = startTime,
        )
        return if (carriage.isSuccessful && carriage.body() != null) {
            var body = carriage.body()
            var count = 1
            var result: String?
            do {
                body = apiRetrofit.checkRID(rid = body?.RID!!).body()
                count++
                result = body?.result
            } while (result == "RID")
            Timber.e("count $count")
            Timber.e(body!!.toString())
            body
        } else {
            null
        }
    }

}
