package com.example.myapplication2.retrofitService


import com.example.myapplication2.UserResponse
import com.example.myapplication2.UserResponseItem
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getUser(): Flow<NetworkResult<UserResponse>> {
        return flow<NetworkResult<UserResponse>> {
            emit(safeApiCall { remoteDataSource.getUser() })
        }.flowOn(Dispatchers.IO)
    }
}