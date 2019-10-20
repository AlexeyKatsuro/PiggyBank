package com.alexeykatsuro.piggybank.data.entries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexeykatsuro.piggybank.data.entities.Currency
import java.util.Date

@Entity(tableName = "meal")
data class MealEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "amount_foreign") val amountForeign: Float,
    @ColumnInfo(name = "amount_national") val amountNational: Float,
    @ColumnInfo(name = "rate") val rate: Float,
    @ColumnInfo(name = "mealtime") val mealtime: Date,
    @ColumnInfo(name = "currency") val currency: Currency
)