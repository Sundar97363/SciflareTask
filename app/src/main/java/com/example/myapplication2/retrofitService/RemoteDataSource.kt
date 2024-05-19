package com.example.myapplication2.retrofitService

import javax.inject.Inject


class RemoteDataSource  @Inject constructor(private val apiService: ApiService) {
    suspend fun getUser() =
        apiService.getDetails()
}