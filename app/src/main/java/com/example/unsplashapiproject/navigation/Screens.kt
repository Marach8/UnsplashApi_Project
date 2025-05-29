package com.example.unsplashapiproject.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Search: Screens("search_screen")
}