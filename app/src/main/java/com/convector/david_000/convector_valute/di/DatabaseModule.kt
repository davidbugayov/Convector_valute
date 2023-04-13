package com.convector.david_000.convector_valute.di

import android.content.Context
import androidx.room.Room
import com.convector.david_000.convector_valute.data.local.CurrenciesDao
import com.convector.david_000.convector_valute.data.local.CurrenciesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CurrenciesDatabase {
        return Room.databaseBuilder(
            appContext,
            CurrenciesDatabase::class.java,
            "Currency"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(currenciesDatabase: CurrenciesDatabase): CurrenciesDao {
        return currenciesDatabase.currenciesDao
    }

}