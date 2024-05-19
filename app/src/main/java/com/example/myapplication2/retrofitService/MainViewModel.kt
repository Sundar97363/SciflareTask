package com.example.myapplication2.retrofitService

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.UserResponse
import com.example.myapplication2.UserResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {
    private val _response: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<UserResponse>> = _response
    fun fetchDogResponse() = viewModelScope.launch {
        repository.getUser().collect { values ->
            _response.value = values
        }
    }
}