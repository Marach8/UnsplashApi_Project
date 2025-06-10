package com.example.unsplashapiproject.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.unsplashapiproject.navigation.Screens
import com.example.unsplashapiproject.screens.commons.ListUnsplashItems

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
){
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()
    Scaffold (
        topBar = {
            HomeAppBar(
                onSearchClicked = {
                    navController.navigate(Screens.Search.route)
                }
            )
        },
        content = {padding ->
            ListUnsplashItems(
                unsplashImages = getAllImages,
                paddingValues = padding
            )
        }
    )
}