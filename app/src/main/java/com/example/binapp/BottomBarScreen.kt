package com.example.binapp

import androidx.compose.material.icons.Icons

sealed class BottomBarScreen( // representation of different screens
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.main_icon
    )

    object LatestsRequests: BottomBarScreen(
        route = "key",
        title = "Key",
        icon = R.drawable.latest_icon
    )
}