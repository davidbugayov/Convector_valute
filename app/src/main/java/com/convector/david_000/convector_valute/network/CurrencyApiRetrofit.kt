package com.convector.david_000.convector_valute.network

import android.content.Context
import com.convector.david_000.convector_valute.network.responce.ConvertDto
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiRetrofit {

    @GET("exchangerates_data/convert")
    suspend fun convert(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Double,
        @Query("date") date: String?,
    ): Response<ConvertDto>

    class CurrencyApi(
        baseUrl: String,
        outputClass: Class<CurrencyApiRetrofit>,
        defaultInterceptors: ArrayList<Interceptor>
    ) : IRetrofitApiFactory<CurrencyApiRetrofit>(baseUrl, outputClass, defaultInterceptors)
}
