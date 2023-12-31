package com.derandecker.uhaulcodingchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.derandecker.uhaulcodingchallenge.models.NewPost
import com.derandecker.uhaulcodingchallenge.models.Post
import com.derandecker.uhaulcodingchallenge.network.UserApi
import com.derandecker.uhaulcodingchallenge.ui.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserPostsViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _userPosts = MutableStateFlow(emptyList<Post>())
    val userPosts: StateFlow<List<Post>> = _userPosts

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("CoroutineExceptionHandler UserPostsViewmodel ViewModelScope:", "$exception")
    }

    fun getUserPosts(userId: Int) {
        // prevent reloading from network during config change such as screen rotation
        if (_userPosts.value.isEmpty()) {
            viewModelScope.launch {
                try {
                    withContext(Dispatchers.IO + handler) {
                        _userPosts.value = UserApi.retrofitService.getUserPosts(userId = userId)
                        _uiState.value = UiState.Success
                    }
                } catch (e: java.lang.Exception) {
                    Log.d("NetworkApi", e.toString())
                    _uiState.value = UiState.Error
                }
            }
        }
    }

    // Unfinished due to time constraints
    // Would likely include this in a new ViewModel for the "AddPostScreen" instead
    fun addNewPost(newPost: NewPost): String {
        return if (Post().id != 0) {
            "Posted successfully"
        } else "Post unsccuessful. Try again."
        // Incomplete code above for illustration only.
        // Server returns json String (converted to Post object by Moshi) with id number included.
        // I would handle that here and return status message to UI
        // indicating to user if the post was successful
    }
}

