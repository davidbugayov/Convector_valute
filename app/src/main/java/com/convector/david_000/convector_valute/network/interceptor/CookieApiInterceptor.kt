package com.convector.david_000.convector_valute.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CookieApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val isSession = originalResponse.request.url.toString().contains("checkSeats")
        if (isSession && originalResponse.headers("Set-Cookie").isNotEmpty()) {
            originalResponse.headers("Set-Cookie").forEach {
                list.add("Set-Cookie" to it)
            }
        }
        return originalResponse
    }

    companion object {
        val list = arrayListOf<Pair<String, String>>()
    }
}


