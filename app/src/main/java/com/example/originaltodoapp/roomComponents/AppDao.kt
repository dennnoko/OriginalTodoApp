package com.example.originaltodoapp.roomComponents

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert
    suspend fun insertTask(task: AppEntity)
    @Delete
    suspend fun delete(task: AppEntity)

    @Query("select * from taskEntity")
    fun getAll(): Flow<List<AppEntity>>
}