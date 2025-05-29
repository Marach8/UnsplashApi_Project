package com.example.unsplashapiproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.unsplashapiproject.screens.home.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        composable(route = Screens.Home.route){
            HomeScreen(navController = navController)
        }

        composable(route = Screens.Search.route){
            //SearchScreen(navController = navController)
        }
    }
}
