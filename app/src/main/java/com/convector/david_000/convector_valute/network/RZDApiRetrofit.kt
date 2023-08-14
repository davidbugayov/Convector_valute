package com.convector.david_000.convector_valute.network

import com.convector.david_000.convector_valute.data.remote.responce.SuggestDTO
import com.convector.david_000.convector_valute.data.remote.responce.TicketsDto
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RZDApiRetrofit {

    @GET("suggester")
    suspend fun stations(
        @Query("stationNamePart")stationNamePart:String,
        @Query("lang") lang:String = "ru",
        @Query("compactMode")compactMode:Char = 'y',
    ): Response<List<SuggestDTO>?>


    @GET("timetable/public")
    suspend fun tickets(
        @Query("layer_id") layerId:Long = 5827,
        @Query("dir") dir:Int = 0,
        @Query("tfl")tfl:Int = 3,
        @Query("checkSeats") checkSeats:Int = 5827,
        @Query("code0") code0:Long = 2004000,
        @Query("code1")code1:Long = 2000000,
        @Query("dt0")dt0:String = "15.08.2023",
        @Query("md")md:Int = 1,
    ): Response<TicketsDto>

    class RZDApi(
        baseUrl: String,
        outputClass: Class<RZDApiRetrofit>,
        defaultInterceptors: ArrayList<Interceptor>
    ) : IRetrofitApiFactory<RZDApiRetrofit>(baseUrl, outputClass, defaultInterceptors)
}
