package com.convector.david_000.convector_valute.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Dao
interface RZDDao {

    @Query("SELECT * FROM Stations")
    suspend fun getStations(): List<Stations>

    @Query("SELECT * FROM Stations WHERE stationName LIKE '%' || :stationNamePart || '%'")
    suspend fun getStationsByName(stationNamePart: String): List<Stations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<Stations>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimetable(tp: TPSheet)

    @Query("SELECT * FROM Timesheet")
    suspend fun getTimesheet(): List<TPSheet>

}


@Database(entities = [Stations::class, TPSheet::class], version = 2, exportSchema = false)
@TypeConverters(TpItemDtoConverter::class)
abstract class RZDDatabase : RoomDatabase() {
    abstract val RZDDao: RZDDao
}