package com.convector.david_000.convector_valute.network

import com.convector.david_000.convector_valute.data.remote.responce.SuggestDTO
import com.convector.david_000.convector_valute.data.remote.responce.TimesheetDto
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RZDApiRetrofit {

    @GET("suggester")
    suspend fun stations(
        @Query("stationNamePart") stationNamePart: String,
        @Query("lang") lang: String = "ru",
        @Query("compactMode") compactMode: Char = 'y',
    ): Response<List<SuggestDTO>?>


    @POST("timetable/public/ru")
    suspend fun timetable(
        @Query("layer_id") layerId: Long = 5827,
        @Query("dir") dir: Int = 0,// 0 - только в один конец, 1 - туда-обратно
        @Query("tfl") tfl: Int = 3,// 3 - поезда и электрички, 2 - электрички, 1 - поезда
        @Query("checkSeats") checkSeats: Int = 1,//1 - только с билетами, 0 - все поезда
        @Query("code0") code0: Int = 2001025, // код станции отправления
        @Query("code1") code1: Int = 2014370,// код станции прибытия
        @Query("dt0") dt0: String = "04.11.2023",
        @Query("md") md: Int = 0,// 0 - без пересадок, 1 - с пересадками
    ): Response<TimesheetDto>

    @POST("timetable/public/ru")
    suspend fun checkRID(
        @Query("layer_id") layerId: Int = 5827,
        @Query("rid") rid: Long,
    ): Response<TimesheetDto>

    @POST("timetable/public")
    suspend fun carriage(
        @Query("layer_id") layerId: Long = 5827,
        @Query("dir") dir: Int = 0,// 0 - только в один конец, 1 - туда-обратно
        @Query("tfl") tfl: Int = 3,// 3 - поезда и электрички, 2 - электрички, 1 - поезда
        @Query("checkSeats") checkSeats: Int = 1,//1 - только с билетами, 0 - все поезда
        @Query("code0") code0: Long, // код станции отправления
        @Query("code1") code1: Long,// код станции прибытия
        @Query("dt0") dt0: String,
        @Query("time0") time0: String,
        @Query("tnum0") tnum0: String,
        @Query("md") md: Int = 0,// 0 - без пересадок, 1 - с пересадками
    ): Response<TimesheetDto>


    class RZDApi(
        baseUrl: String,
        outputClass: Class<RZDApiRetrofit>,
        defaultInterceptors: ArrayList<Interceptor>
    ) : IRetrofitApiFactory<RZDApiRetrofit>(baseUrl, outputClass, defaultInterceptors)
}
