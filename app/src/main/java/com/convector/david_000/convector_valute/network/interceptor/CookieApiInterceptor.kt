package com.convector.david_000.convector_valute.network.interceptor

import com.convector.david_000.convector_valute.network.IRetrofitApiFactory.Companion.cookieHelper
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class CookieApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.apply {
            header("Content-Type", "application/x-www-form-urlencoded ");
            header("Accept", "application/json");
            header("User-Agent", "GuzzleHttp/7");
            header("Referer", "https://rzd.ru")

            if (list.isNotEmpty()) {
                list.add("lang" to "ru")
                list.add("AuthFlag" to "false")
                list.forEach {
                    cookieHelper.setCookie("https://pass.rzd.ru/", it.first, it.second)
                }

                // header("Cookie", cookieHeader())
            }
        }


        val originalResponse = chain.proceed(requestBuilder.build())
        val isSession = originalResponse.request.url.toString().contains("checkSeats")
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
        cookieHeader.append("lang=ru; ")
        cookieHeader.append("AuthFlag=false; ")

        for (cookie in list) {

            if (cookie.first == "ClientUid") {
                cookieHeader.append(cookie.first).append('=').append(cookie.second);
                cookieHeader.append("; ");
            }
            if (cookie.first == "JSESSIONID") {
                cookieHeader.append(cookie.first).append('=').append(cookie.second);
                cookieHeader.append("; ");

            }
            if (cookie.first == "session-cookie") {
                cookieHeader.append(cookie.first).append('=').append(cookie.second)
            }
        }
        Timber.e(cookieHeader.toString() + "  \n")
        return cookieHeader.toString();
    }
}


