package com.example.unsplashapiproject.screens.search

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.unsplashapiproject.screens.commons.ListUnsplashItems

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchVModel: SearchViewModel = hiltViewModel()
){
    val searchQuery by searchVModel.searchQuery
    val searchImages = searchVModel.searchImages.collectAsLazyPagingItems()

    Scaffold (
        topBar = {
            SearchWidget(
                hintText = searchQuery,
                onSearchClick = {searchVModel.searchHeroes(query = it)},
                onInputChanged = {searchVModel.updateSearchQuery(query = it)},
                onClosedIconTapped = {navController.popBackStack()}
            )
        },
        content = {
            ListUnsplashItems(
                unsplashImages = searchImages,
                paddingValues = it
            )
        }
    )
}