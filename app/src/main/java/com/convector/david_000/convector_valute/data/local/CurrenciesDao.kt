package com.convector.david_000.convector_valute.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Dao
interface CurrenciesDao {

    @Query("SELECT * FROM Currency")
    suspend fun getCurrencies():SymbolsItem?

    @Insert
    suspend fun insertCurrencies(symbols: SymbolsItem)

    @Update
    suspend fun updateCurrencies(symbols: SymbolsItem)

    @Delete
    suspend fun deleteCurrencies(symbols: SymbolsItem)

}


@Database(entities = [SymbolsItem::class,], version = 1, exportSchema = false)
abstract class CurrenciesDatabase : RoomDatabase() {
    abstract val currenciesDao: CurrenciesDao
}