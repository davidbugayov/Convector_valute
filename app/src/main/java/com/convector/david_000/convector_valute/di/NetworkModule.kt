package com.convector.david_000.convector_valute.di

import com.convector.david_000.convector_valute.network.RZDApiRetrofit
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
    fun provideApiRetrofit(): RZDApiRetrofit {
        return RZDApiRetrofit.RZDApi(
            "https://pass.rzd.ru/",
            RZDApiRetrofit::class.java,
            defaultInterceptors =  arrayListOf(CurrencyApiInterceptor())
        ).create(true,)

    }
}