package com.convector.david_000.convector_valute.di

//import android.content.Context
//import androidx.room.Room
//import com.aregyan.github.database.UsersDao
//import com.aregyan.github.database.UsersDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton

//@InstallIn(SingletonComponent::class)
//@Module
//object DatabaseModule {
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): CurrencyDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            CurrencyDatabase::class.java,
//            "Currency"
//        ).fallbackToDestructiveMigration().build()
//    }
//
//    @Provides
//    fun provideChannelDao(usersDatabase: CurrencyDatabase): CurrencyDao {
//        return usersDatabase.usersDao
//    }

//}