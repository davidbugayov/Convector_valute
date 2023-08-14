package com.convector.david_000.convector_valute.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Dao
interface RZDDao {

    @Query("SELECT * FROM Stations WHERE stationName= :stationNamePart")
    suspend fun getStations(stationNamePart: String):List<Stations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<Stations>)

    @Update
    suspend fun updateStations(stations: List<Stations>)
//
//    @Delete
//    suspend fun deleteCurrencies(symbols: SymbolsItem)

}


@Database(entities = [Stations::class,], version = 1, exportSchema = false)
abstract class RZDDatabase : RoomDatabase() {
    abstract val RZDDao: RZDDao
}