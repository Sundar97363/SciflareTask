package com.example.myapplication2.helper

import androidx.room.TypeConverter
import com.example.myapplication2.UserResponse
import com.example.myapplication2.UserResponseItem
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJsonString(value: List<UserResponseItem>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<UserResponseItem>::class.java).toList()
}