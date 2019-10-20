package com.alexeykatsuro.piggybank.data

import androidx.room.TypeConverter
import com.alexeykatsuro.piggybank.data.entities.Currency
import java.util.Date


class Converters {
    @TypeConverter
    fun toTimestamp(date: Date): Long = date.time

    @TypeConverter
    fun toDate(time: Long): Date = Date(time)

    @TypeConverter
    fun toString(currency: Currency): String = currency.name

    @TypeConverter
    fun toCurrency(string: String): Currency = Currency.valueOf(string)
}