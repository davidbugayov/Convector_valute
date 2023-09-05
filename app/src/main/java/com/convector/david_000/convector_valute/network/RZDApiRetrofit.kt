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


    //@FormUrlEncoded
    @POST("timetable/public/ru")
    suspend fun timetable(
        @Query("layer_id") layerId: Long = 5827,
        @Query("dir") dir: Int = 0,// 0 - только в один конец, 1 - туда-обратно
        @Query("tfl") tfl: Int = 3,// 3 - поезда и электрички, 2 - электрички, 1 - поезда
        @Query("checkSeats") checkSeats: Int = 1,//1 - только с билетами, 0 - все поезда
        @Query("code0") code0: Int = 2004000, // код станции отправления
        @Query("code1") code1: Int = 2000000,// код станции прибытия
        @Query("dt0") dt0: String = "09.09.2023",
        // @Field("dt1") dt1: String = "01.09.2023",
        // @Field("time0")time0:String="00:00",
        @Query("md") md: Int = 1,// 0 - без пересадок, 1 - с пересадками
        ///  @Query("STRUCTURE_ID")STRUCTURE_ID:Int = 735,
        @Query("rid") rid: Long? = null,
    ): Response<TimesheetDto>

    @POST("timetable/public/ru")
    suspend fun timetableRID(
        @Query("layer_id") layerId: Int = 5827,
        @Query("rid") rid: Long,
    ): Response<TimesheetDto>

    @GET("services/route/basicRoute")
    suspend fun trainRoutes(
        //  @Header("Set-Cookie") list: List< String> = CookieApiInterceptor.list,
        // @Header("session-cookie") Session: String = "177dc6c1ea54d2ba0abed93e18991a244ba3704fc064efb8c27645f30936df71660a2365f85ef19c12169703352bb7f6",
        @Query("layer_id") layerId: Long = 5827,
        @Query("rid") rid: Long,
    ): Response<TimesheetDto>

    class RZDApi(
        baseUrl: String,
        outputClass: Class<RZDApiRetrofit>,
        defaultInterceptors: ArrayList<Interceptor>
    ) : IRetrofitApiFactory<RZDApiRetrofit>(baseUrl, outputClass, defaultInterceptors)
}
