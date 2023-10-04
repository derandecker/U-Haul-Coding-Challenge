package com.derandecker.uhaulcodingchallenge.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.derandecker.uhaulcodingchallenge.R
import com.derandecker.uhaulcodingchallenge.models.User
import com.derandecker.uhaulcodingchallenge.ui.theme.UHaulCodingChallengeTheme
import com.derandecker.uhaulcodingchallenge.viewmodel.HomeViewModel


enum class AppScreens(@StringRes val title: Int) {
    Users(title = R.string.users),
    Posts(title = R.string.user_posts),
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val userList = viewModel.userList.collectAsState().value
    val uiState = viewModel.uiState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = AppScreens.Users.name,
    ) {
        composable(route = AppScreens.Users.name) {
            UsersScreen(
                userList = userList,
                uiState = uiState,
                onClick = { navController.navigate("posts/$it") }
            )
        }
        composable(
            route = "posts/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("userId")?.let { UserPostsScreen(it) }
        }
    }
}

@Composable
fun UsersScreen(userList: List<User>, uiState: UiState, onClick: (Int) -> Unit) {
    when (uiState) {
        UiState.Loading -> Text("Loading...")
        UiState.Success -> UsernameAndAddressList(userList = userList, onClick = onClick)
        UiState.Error -> Text("Error loading Users")
    }
}

@Composable
fun UsernameAndAddressList(userList: List<User>, onClick: (Int) -> Unit) {
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
                    )
                    .clickable { onClick(it.id) },
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
        UsersScreen(uiState = UiState.Error, userList = testUserList) {}
    }
}

@Pixel5Preview
@Composable
fun LoadingPreview() {
    UHaulCodingChallengeTheme {
        UsersScreen(uiState = UiState.Loading, userList = testUserList) {}
    }
}

@Preview(
    name = "Pixel5WithSystemUi",
    showBackground = true,
    device = "id:pixel_5",
    showSystemUi = true
)
annotation class Pixel5Preview
