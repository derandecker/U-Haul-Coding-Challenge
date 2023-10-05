package com.derandecker.uhaulcodingchallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.derandecker.uhaulcodingchallenge.models.Post
import com.derandecker.uhaulcodingchallenge.viewmodel.UserPostsViewModel

@Composable
fun UserPostsScreen(
    userId: Int,
    viewModel: UserPostsViewModel = viewModel()
) {
    val postList = viewModel.userPosts.collectAsState().value
    val uiState = viewModel.uiState.collectAsState().value

    viewModel.getUserPosts(userId)

    // FloatingActionButton is Incomplete due to time constraints.
    // FAB would callback to HomeScreen with a lambda
    // that performs a navigation action to a NewPost screen.
    // NewPost screen includes title and body TextField and a "Post now" button
    // which would call the ViewModel addNewPost(newPost: NewPost) method
    // (currently in UserPostsViewModel, but I would create a new AddPostViewModel for that screen
    Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
        FloatingActionButton(
            onClick = { },
            Modifier.padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, "New post")
        }
    }

    when (uiState) {
        UiState.Loading -> Text("Loading...")
        UiState.Success -> UserPosts(postList = postList)
        UiState.Error -> Text("Error loading Posts")
    }
}


@Composable
fun UserPosts(postList: List<Post>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(postList) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 8.dp,
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = it.title)
                    Text(text = it.body)
                }
            }
        }
    }
}
