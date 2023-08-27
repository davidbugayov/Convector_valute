package com.convector.david_000.convector_valute.network.interceptor

import java.util.concurrent.ConcurrentHashMap
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/*  Cookie Handling Lib for OkHttp3
 *
 *  Copyright (c) 2017 Tom Misawa, riversun.org@gmail.com
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a
 *  copy of this software and associated documentation files (the "Software"),
 *  to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *  DEALINGS IN THE SOFTWARE.
 *
 */


/**
 *
 * Cookie Helper for OkHttp3 <br></br>
 * <br></br>
 *
 *
 * usage example:<br></br>
 *
 * <pre>
 * `
 * String url = "https://example.com/webapi";
 *
 * OkHttp3CookieHelper cookieHelper = new OkHttp3CookieHelper();
 * cookieHelper.setCookie(url, "cookie_name", "cookie_value");
 *
 * OkHttpClient client = new OkHttpClient.Builder()
 * .cookieJar(cookieHelper.cookieJar())
 * .build();
 *
 * Request request = new Request.Builder()
 * .url(url)
 * .build();
` *
</pre> *
 *
 * @author Tom Misawa (riversun.org@gmail.com)
 */
class OkHttp3CookieHelper {
    private val mServerCookieStore: MutableMap<String, MutableList<Cookie>> = ConcurrentHashMap()
    private val mClientCookieStore: MutableMap<String, MutableList<Cookie>> = ConcurrentHashMap()
    private val mCookieJar: CookieJar = object : CookieJar {
        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            var serverCookieList = mServerCookieStore[url.host]
            if (serverCookieList == null) {
                serverCookieList = ArrayList()
            }
            val clientCookieStore: List<Cookie>? = mClientCookieStore[url.host]
            if (clientCookieStore != null) {
                serverCookieList.addAll(clientCookieStore)
            }
            return serverCookieList ?: ArrayList()
        }

        override fun saveFromResponse(url: HttpUrl, unmodifiableCookieList: List<Cookie>) {
            // Why 'new ArrayList<Cookie>'?
            // Since 'unmodifiableCookieList' can not be changed, create a new
            // one
            mServerCookieStore[url.host] = ArrayList(unmodifiableCookieList)

            // The persistence code should be described here if u want.
        }
    }

    /**
     * Set cookie
     *
     * @param url
     * @param cookie
     */
    fun setCookie(url: String?, cookie: Cookie?) {
        val host = url!!.toHttpUrlOrNull()!!.host
        var cookieListForUrl = mClientCookieStore[host]
        if (cookieListForUrl == null) {
            cookieListForUrl = ArrayList()
            mClientCookieStore[host] = cookieListForUrl
        }
        putCookie(cookieListForUrl, cookie)
    }

    /**
     * Set cookie
     *
     * @param url
     * @param cookieName
     * @param cookieValue
     */
    fun setCookie(url: String?, cookieName: String, cookieValue: String) {
        val httpUrl = url!!.toHttpUrlOrNull()
        setCookie(url, Cookie.parse(httpUrl!!, "$cookieName=$cookieValue"))
    }

    /**
     * Set cookie
     *
     * @param httpUrl
     * @param cookieName
     * @param cookieValue
     */
    fun setCookie(httpUrl: HttpUrl, cookieName: String, cookieValue: String) {
        setCookie(httpUrl.host, Cookie.parse(httpUrl, "$cookieName=$cookieValue"))
    }

    /**
     * Returns CookieJar
     *
     * @return
     */
    fun cookieJar(): CookieJar {
        return mCookieJar
    }

    private fun putCookie(storedCookieList: MutableList<Cookie>, newCookie: Cookie?) {
        var oldCookie: Cookie? = null
        for (storedCookie in storedCookieList) {

            // create key for comparison
            val oldCookieKey = storedCookie.name + storedCookie.path
            val newCookieKey = newCookie!!.name + newCookie.path
            if (oldCookieKey == newCookieKey) {
                oldCookie = storedCookie
                break
            }
        }
        if (oldCookie != null) {
            storedCookieList.remove(oldCookie)
        }
        storedCookieList.add(newCookie!!)
    }
}