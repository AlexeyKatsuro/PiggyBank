package com.alexeykatsuro.piggybank.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entries: List<T>)

    @Update
    suspend fun update(entry: T)

    @Update
    suspend fun updateAll(entries: List<T>)

    @Delete
    suspend fun delete(entry: T)

}