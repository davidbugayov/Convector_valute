package com.convector.david_000.convector_valute.network

import com.convector.david_000.convector_valute.data.remote.responce.SuggestDTO
import com.convector.david_000.convector_valute.data.remote.responce.TicketsDto
import com.convector.david_000.convector_valute.network.interceptor.CookieApiInterceptor
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RZDApiRetrofit {

    @GET("suggester")
    suspend fun stations(
        @Query("stationNamePart") stationNamePart: String,
        @Query("lang") lang: String = "ru",
        @Query("compactMode") compactMode: Char = 'y',
    ): Response<List<SuggestDTO>?>


    @GET("timetable/public")
    suspend fun timetable(
        @Query("layer_id") layerId: Long = 5827,
        @Query("dir") dir: Int = 0,// 0 - только в один конец, 1 - туда-обратно
        @Query("tfl") tfl: Int = 3,// 3 - поезда и электрички, 2 - электрички, 1 - поезда
        @Query("checkSeats") checkSeats: Int = 0,//1 - только с билетами, 0 - все поезда
        @Query("code0") code0: Long = 2004000, // код станции отправления
        @Query("code1") code1: Long = 2000000,// код станции прибытия
        @Query("dt0") dt0: String = "15.09.2023",
        //@Query("time0")time0:String="00:00",
        @Query("md") md: Int = 0,// 0 - без пересадок, 1 - с пересадками
    ): Response<TicketsDto>

    @GET("timetable/public")
    suspend fun timetableRID(
        @Header("Cookie") list: List<Pair<String, String>> = CookieApiInterceptor.list,
        //  @Query("STRUCTURE_ID") structure: Int = 735,
        @Query("layer_id") layerId: Int = 5827,
        @Query("rid") rid: Long,
    ): Response<TicketsDto>

    @GET("services/route/basicRoute")
    suspend fun trainRoutes(
        //  @Header("Set-Cookie") list: List< String> = CookieApiInterceptor.list,
        // @Header("session-cookie") Session: String = "177dc6c1ea54d2ba0abed93e18991a244ba3704fc064efb8c27645f30936df71660a2365f85ef19c12169703352bb7f6",
        @Query("layer_id") layerId: Long = 5827,
        @Query("rid") rid: Long,
    ): Response<TicketsDto>

    class RZDApi(
        baseUrl: String,
        outputClass: Class<RZDApiRetrofit>,
        defaultInterceptors: ArrayList<Interceptor>
    ) : IRetrofitApiFactory<RZDApiRetrofit>(baseUrl, outputClass, defaultInterceptors)
}
