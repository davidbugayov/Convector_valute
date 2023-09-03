package com.convector.david_000.convector_valute.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.convector.david_000.convector_valute.data.remote.responce.TimesheetDto

@Entity(tableName = "Timesheet")
data class Timesheet(
    val timesheet: TimesheetDto,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)