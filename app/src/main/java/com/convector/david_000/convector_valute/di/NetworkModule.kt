package com.convector.david_000.convector_valute.di

import com.convector.david_000.convector_valute.network.CurrencyApiRetrofit
import com.convector.david_000.convector_valute.network.interceptor.CurrencyApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): CurrencyApiRetrofit {
        return CurrencyApiRetrofit.CurrencyApi(
            "https://api.apilayer.com/",
            CurrencyApiRetrofit::class.java,
            defaultInterceptors =  arrayListOf(CurrencyApiInterceptor())
        ).create(true,)

    }
}