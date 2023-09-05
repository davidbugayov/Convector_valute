package com.convector.david_000.convector_valute.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.convector.david_000.convector_valute.data.remote.responce.TpItemDto

@Entity(tableName = "Timesheet")
data class TPSheet(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "tp_column")
    val tpSheetItem: TpItemDto
)