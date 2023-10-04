package com.derandecker.uhaulcodingchallenge.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.derandecker.uhaulcodingchallenge.models.User
import com.derandecker.uhaulcodingchallenge.ui.theme.UHaulCodingChallengeTheme
import com.derandecker.uhaulcodingchallenge.viewmodel.HomeViewModel
import com.derandecker.uhaulcodingchallenge.viewmodel.UiState


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    val userList = viewModel.userList.collectAsState().value
    val uiState = viewModel.uiState.collectAsState().value
    MainScreen(userList = userList, uiState = uiState)
}

@Composable
fun MainScreen(userList: List<User>, uiState: UiState) {
    when (uiState) {
        UiState.Loading -> Text("Loading...")
        UiState.Success -> UsernameAndAddressList(userList = userList)
        UiState.Error -> Text("Error loading Users")
    }
}

@Composable
fun UsernameAndAddressList(userList: List<User>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(userList) {
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
                    Text(text = it.username)
                    Text(text = it.address.street)
                    Text(text = it.address.suite)
                    Text(text = it.address.city)
                    Text(text = it.address.zipcode)
                }
            }
        }
    }
}

val testUserList = listOf<User>()

@Pixel5Preview
@Composable
fun ErrorPreview() {
    UHaulCodingChallengeTheme {
        MainScreen(uiState = UiState.Error, userList = testUserList)
    }
}

@Pixel5Preview
@Composable
fun LoadingPreview() {
    UHaulCodingChallengeTheme {
        MainScreen(uiState = UiState.Loading, userList = testUserList)
    }
}

@Preview(
    name = "Pixel5WithSystemUi",
    showBackground = true,
    device = "id:pixel_5",
    showSystemUi = true
)
annotation class Pixel5Preview
