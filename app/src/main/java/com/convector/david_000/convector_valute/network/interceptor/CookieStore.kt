package com.convector.david_000.convector_valute.network.interceptor

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import timber.log.Timber

class CookieStore : CookieJar {
    private val mCookieStore = HashMap<String, List<Cookie>>()
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        mCookieStore[url.host] = cookies
        Timber.e("saveFromResponse")
        Timber.e(cookies.toString())
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        Timber.e("loadForRequest")
        return mCookieStore[url.host]!!
    }

    fun clear() {
        mCookieStore.clear()
    }
}