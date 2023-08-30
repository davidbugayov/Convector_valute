package com.convector.david_000.convector_valute.network.interceptor

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class CookieApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.apply {
            addHeader("Content-Type", "application/x-www-form-urlencoded ")
            addHeader("Accept", "application/json")
            addHeader("User-Agent", "GuzzleHttp/7")

            if (list.isNotEmpty()) {
                addHeader("Cookie", cookieHeader())
            }
            addHeader("Content-Length", "97")
        }


        val originalResponse = chain.proceed(requestBuilder.build())
        val isSession =
            (originalResponse.request.body as? FormBody)?.name(3)?.contains("checkSeats") ?: false
        if (isSession && originalResponse.headers("Set-Cookie").isNotEmpty() && list.isEmpty()) {
            originalResponse.headers("Set-Cookie").forEach {
                val commaDotDelimiter = it.split("; ")
                val pair = commaDotDelimiter[0].split("=")
                list.add(pair[0] to pair[1])
            }
        }
        return originalResponse
    }

    companion object {
        val list = arrayListOf<Pair<String, String>>()
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

        Timber.e(cookieHeader.toString() + "  \n")
        return cookieHeader.toString();
    }
}


