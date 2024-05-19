package com.example.myapplication2.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication2.UserResponse
import com.example.myapplication2.UserResponseItem

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    @ColumnInfo(name = "model_list") var yourModelList: List<UserResponseItem>,
//    val username: String,
//    val email: String,
//    val mobile: String,
//    val gender: String,
)