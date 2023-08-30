package com.convector.david_000.convector_valute.network

import com.convector.david_000.convector_valute.network.deserializers.DateDeserializer
import com.convector.david_000.convector_valute.network.deserializers.NullOnEmptyConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val TIMEOUT = 1L

/**
 * An interface that describes generic factory for retrofit api interface
 *
 * @param RetrofitInterface concrete retrofit interface we building a factory for
 *
 * @property baseUrl readonly field that contains an url base for retrofit calls
 * @property outputClass used by default implementation of 'create' function to instantiate retrofit object
 *
 */
abstract class IRetrofitApiFactory<RetrofitInterface>(
    private val baseUrl: String,
    private val outputClass: Class<RetrofitInterface>,
    private val defaultInterceptors: ArrayList<Interceptor>
) {

    /**
     * Creates an instance of retrofit interface
     * @param allowLogs - could be used to override default logging behavior
     */
    fun create(
        allowLogs: Boolean,
        additionalInterceptors: List<Interceptor>? = null,
        gson: Gson = GsonBuilder().apply {
            registerTypeAdapter(Date::class.java, DateDeserializer())
        }.create(),
    ): RetrofitInterface {
        additionalInterceptors?.let {
            defaultInterceptors.addAll(it)
        }

        val retrofit: Retrofit = if (allowLogs) {
            createWithLogger(gson, additionalInterceptors)
        } else {
            createDefault(gson)
        }

        return retrofit.create(outputClass)
    }

    private fun createHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        interceptors.forEach { interceptor ->
            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    private fun createDebugHttpClient(additionalInterceptors: List<Interceptor>? = null): OkHttpClient {
        val logger = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.MINUTES)
            .connectTimeout(TIMEOUT, TimeUnit.MINUTES)

        additionalInterceptors?.forEach {
            builder.addInterceptor(it)
        }
        defaultInterceptors.forEach {
            builder.addInterceptor(it)
        }
        builder.addInterceptor(logger)
        return builder.build()
    }

    private fun createDefault(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(createHttpClient(defaultInterceptors))
            .baseUrl(baseUrl)
            .addConverterFactories(gson)
            .build()
    }

    private fun createWithLogger(
        gson: Gson,
        additionalInterceptors: List<Interceptor>? = null
    ): Retrofit {
        return Retrofit.Builder()
            .client(createDebugHttpClient(additionalInterceptors))
            .baseUrl(baseUrl)
            .addConverterFactories(gson)
            .build()
    }

}

fun Retrofit.Builder.addConverterFactories(gson: Gson): Retrofit.Builder {
    this.addConverterFactory(NullOnEmptyConverterFactory())
    this.addConverterFactory(ScalarsConverterFactory.create())
    this.addConverterFactory(GsonConverterFactory.create(gson))
    return this
}
