package com.convector.david_000.convector_valute.network.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class DateDeserializer : JsonDeserializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date? {
        for (formatter in formatters) {
            try {
                return formatter.parse(element.asString)
            } catch (e: Exception) {
            }
        }
        return null
    }

    companion object {
        private val formatter = SimpleDateFormat("dd MM yyyy", Locale.getDefault())
        private val emergencyBlockFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).also {
            it.timeZone = TimeZone.getTimeZone("GMT-0:00")
        }
        private val timestampFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.getDefault())
        private val timestampFormatterNoMs = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ssz", Locale.US)
        private val timestampFormatterNoMsNoTz = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        private val formatters = listOf(formatter, timestampFormatter, timestampFormatterNoMs, timestampFormatterNoMsNoTz, emergencyBlockFormat)
    }
}
