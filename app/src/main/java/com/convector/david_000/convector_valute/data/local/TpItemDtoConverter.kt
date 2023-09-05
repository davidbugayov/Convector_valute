package com.convector.david_000.convector_valute.data.local

import androidx.room.TypeConverter
import com.convector.david_000.convector_valute.data.remote.responce.TpItemDto
import com.google.gson.Gson

class TpItemDtoConverter {
    @TypeConverter
    fun fromTp(tpItemDto: TpItemDto): String {
        return Gson().toJson(tpItemDto)
    }

    @TypeConverter
    fun toTpList(value: String): TpItemDto {
        return Gson().fromJson(value, TpItemDto::class.java)
    }
}