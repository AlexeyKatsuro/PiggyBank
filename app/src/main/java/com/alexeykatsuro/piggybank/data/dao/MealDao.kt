package com.alexeykatsuro.piggybank.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao : BaseDao<MealEntry> {

    @Query("SELECT * FROM meal")
    fun getAllObserve(): Flow<List<MealEntry>>

    @Query("SELECT SUM(amount_foreign) FROM meal")
    fun totalAmountForeignObserve(): Flow<Float?>

    @Query("SELECT SUM(amount_national) FROM meal")
    fun totalAmountNationalObserve(): Flow<Float?>

    @Query("SELECT SUM(amount_foreign) FROM meal")
    suspend fun totalAmountForeign(): Float?

    @Query("SELECT SUM(amount_national) FROM meal")
    suspend fun totalAmountNational(): Float?
}
