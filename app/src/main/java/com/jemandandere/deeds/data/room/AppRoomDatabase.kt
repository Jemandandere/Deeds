package com.jemandandere.deeds.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jemandandere.deeds.model.Deed

@Database(entities = [Deed::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun deedDao(): DeedDao
    companion object {
        @Volatile
        private lateinit var database: AppRoomDatabase

        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase{
            if (!this::database.isInitialized){
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
            }
            return database
        }
    }
}