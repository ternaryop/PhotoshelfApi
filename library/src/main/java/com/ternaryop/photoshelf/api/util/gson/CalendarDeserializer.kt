package com.ternaryop.photoshelf.api.util.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

private val INVALID_CALENDAR = Calendar.getInstance().apply { clear() }

class CalendarDeserializer : JsonDeserializer<Calendar> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Calendar {
        return json?.let {
            DateTimeFormatter.ISO_LOCAL_DATE.parse(it.asString, LocalDate::from).let { localDate ->
                Calendar.getInstance().apply {
                    clear()
                    set(Calendar.YEAR, localDate.year)
                    set(Calendar.DAY_OF_MONTH, localDate.dayOfMonth)
                    set(Calendar.MONTH, localDate.monthValue - 1)
                }
            }
        } ?: INVALID_CALENDAR
    }
}