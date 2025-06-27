package com.example.unsplashapiproject.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.unsplashapiproject.screens.home.HomeScreen
import com.example.unsplashapiproject.screens.search.SearchScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        composable(route = Screens.Home.route){
            HomeScreen(
                navController = navController,
                homeViewModel = hiltViewModel()
            )
        }

        composable(route = Screens.Search.route){
            SearchScreen(navController = navController)
        }
    }
}
