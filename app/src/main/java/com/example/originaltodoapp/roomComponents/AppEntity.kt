package com.example.originaltodoapp.roomComponents

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskEntity")
data class AppEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val detail: String?
)