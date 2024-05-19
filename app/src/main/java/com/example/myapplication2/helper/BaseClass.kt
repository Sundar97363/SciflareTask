package com.example.myapplication2.helper

import android.app.Application
import androidx.room.Room
import com.example.myapplication2.roomDB.AppDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseClass :Application(){
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database"
        ).build()
    }
}