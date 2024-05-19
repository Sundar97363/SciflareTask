package com.example.myapplication2.retrofitService


import com.example.myapplication2.UserResponse
import com.example.myapplication2.UserResponseItem
import com.example.myapplication2.helper.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.BASE_URL)
    suspend fun getDetails():Response<UserResponse>
}