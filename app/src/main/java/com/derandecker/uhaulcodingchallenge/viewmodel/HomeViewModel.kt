package com.derandecker.uhaulcodingchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.derandecker.uhaulcodingchallenge.models.User
import com.derandecker.uhaulcodingchallenge.network.UserApi
import com.derandecker.uhaulcodingchallenge.ui.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _userList = MutableStateFlow(emptyList<User>())
    val userList: StateFlow<List<User>> = _userList

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("CoroutineExceptionHandler HomeViewModel ViewModelScope:", "$exception")
    }

    init {
        viewModelScope.launch {
            // in production app, we would handle the fetching of
            // data inside a repository, pass that instance to ViewModel
            // via dependency injection (manual or Dagger/Hilt) then call
            // the repository here instead of doing the network call directly
            try {
                withContext(Dispatchers.IO + handler) {
                    _userList.value = UserApi.retrofitService.getUsers()
                    _uiState.value = UiState.Success
                }
                // in production app, we would catch each possible Exception by name
                // and handle each accordingly
            } catch (e: java.lang.Exception) {
                Log.d("NetworkApi", e.toString())
                _uiState.value = UiState.Error
            }
        }
    }
}
