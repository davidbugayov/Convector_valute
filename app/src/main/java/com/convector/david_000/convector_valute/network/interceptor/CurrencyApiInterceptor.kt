package com.convector.david_000.convector_valute.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CurrencyApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().apply {
            header("apikey", "Dx4spgp5iO9zVW5Q3tUEQBpZGrMKTjUY")
        }
        return chain.proceed(request.build())
    }
}
