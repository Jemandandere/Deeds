package com.jemandandere.deeds

import android.app.Application
import androidx.navigation.NavController
import com.jemandandere.deeds.data.DatabaseRepository
import com.jemandandere.deeds.data.room.AppRoomDatabase
import com.jemandandere.deeds.data.room.RoomRepository

class App : Application() {

    lateinit var navController: NavController
    lateinit var dbRepository: DatabaseRepository

    companion object {
        @Volatile
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        dbRepository = RoomRepository(AppRoomDatabase.getInstance(this).deedDao())
    }
}