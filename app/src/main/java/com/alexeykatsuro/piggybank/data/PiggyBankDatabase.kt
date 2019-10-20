package com.alexeykatsuro.piggybank.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexeykatsuro.piggybank.data.dao.MealDao
import com.alexeykatsuro.piggybank.data.entries.MealEntry


@Database(
    entities = [
        MealEntry::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PiggyBankDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}