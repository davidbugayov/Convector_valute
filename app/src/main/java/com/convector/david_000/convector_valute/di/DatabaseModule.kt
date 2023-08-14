package com.convector.david_000.convector_valute.di

import android.content.Context
import androidx.room.Room
import com.convector.david_000.convector_valute.data.local.RZDDao
import com.convector.david_000.convector_valute.data.local.RZDDatabase
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): RZDDatabase {
        return Room.databaseBuilder(
            appContext,
            RZDDatabase::class.java,
            "RZD"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDao(database: RZDDatabase): RZDDao {
        return database.RZDDao
    }

}