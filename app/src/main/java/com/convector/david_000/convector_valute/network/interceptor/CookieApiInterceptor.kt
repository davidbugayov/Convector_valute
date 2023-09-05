package com.convector.david_000.convector_valute.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CookieApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.apply {
            if (list.isNotEmpty()) {
                addHeader("Cookie", cookieHeader())
            }
        }

        val originalResponse = chain.proceed(requestBuilder.build())
        if (originalResponse.headers("Set-Cookie").isNotEmpty() && list.isEmpty()) {
            originalResponse.headers("Set-Cookie").forEach {
                val commaDotDelimiter = it.split("; ")
                val pair = commaDotDelimiter[0].split("=")
                list.add(pair[0] to pair[1])
            }
        }
        return originalResponse
    }

    private fun cookieHeader(): String {
        val cookieHeader = StringBuilder();

        for (cookie in list) {

            if (cookie.first == "ClientUid") {
                cookieHeader.append(cookie.first).append('=').append(cookie.second);
                cookieHeader.append("; ");
                cookieHeader.append("AuthFlag=false; ")
            }
            if (cookie.first == "JSESSIONID") {
                cookieHeader.append(cookie.first).append('=').append(cookie.second);
                cookieHeader.append("; ");

            }
            if (cookie.first == "session-cookie") {
                cookieHeader.append(cookie.first).append('=').append(cookie.second)
                cookieHeader.append("; ")
            }
        }
        cookieHeader.append("lang=ru ")

        return cookieHeader.toString();
    }

    companion object {
        val list = arrayListOf<Pair<String, String>>()
    }
}


