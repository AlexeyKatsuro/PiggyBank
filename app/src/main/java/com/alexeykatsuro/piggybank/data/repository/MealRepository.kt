package com.alexeykatsuro.piggybank.data.repository

import com.alexeykatsuro.piggybank.data.dao.MealDao
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MealRepository {
    suspend fun feed(meal: MealEntry)
    fun getMealHistoryObserve(): Flow<List<MealEntry>>
    fun totalAmountForeignObserve(): Flow<Float>
    fun totalAmountNationalObserve(): Flow<Float>
    suspend fun totalAmountForeign(): Float
    suspend fun totalAmountNational(): Float
}

class MealRepositoryImpl(
    private val mealDao: MealDao
) : MealRepository {

    override suspend fun totalAmountForeign(): Float =
        mealDao.totalAmountForeign() ?: 0f

    override suspend fun totalAmountNational(): Float =
        mealDao.totalAmountNational() ?: 0f

    override suspend fun feed(meal: MealEntry) =
        mealDao.insert(meal)

    override fun getMealHistoryObserve(): Flow<List<MealEntry>> =
        mealDao.getAllObserve()

    override fun totalAmountForeignObserve(): Flow<Float> =
        mealDao.totalAmountForeignObserve().map { it ?: 0f }

    override fun totalAmountNationalObserve(): Flow<Float> =
        mealDao.totalAmountNationalObserve().map { it ?: 0f }

}
