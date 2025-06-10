package com.example.unsplashapiproject.screens.commons

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.unsplashapiproject.data.models.UnsplashImage


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListUnsplashItems(
    unsplashImages: LazyPagingItems<UnsplashImage>,
    paddingValues: PaddingValues
){
    Log.d("Error", unsplashImages.loadState.toString())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues,
        //contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(
            count = unsplashImages.itemCount,
            key = { index -> unsplashImages[index]?.id ?: index }
        ){ index ->
            val unsplashImage = unsplashImages[index]
            unsplashImage?.let { RenderUnsplashImage(image = it)}
        }
    }
}
