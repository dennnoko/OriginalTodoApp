package com.example.originaltodoapp.roomComponents

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppEntity::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        private var appDataase: com.example.originaltodoapp.roomComponents.Database? = null

        fun getdb(context: Context): com.example.originaltodoapp.roomComponents.Database {
            if (appDataase == null) {
                appDataase = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.originaltodoapp.roomComponents.Database::class.java,
                    "my_app_database"
                ).build()
            }
            return appDataase!!
        }
    }
}