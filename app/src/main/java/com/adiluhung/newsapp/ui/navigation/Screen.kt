package com.adiluhung.newsapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("About")
}