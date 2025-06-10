package com.example.unsplashapiproject.screens.home

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(onSearchClicked: () -> Unit){
    TopAppBar(
        title = {
            Text(
                text = "Home",
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        Modifier.background(
            color = Color.Yellow
        ),
        actions = {
            IconButton(onClick = onSearchClicked){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Image Icon"
                )
            }
        }
    )
}




@Composable
@Preview
fun TabPreview(){
    HomeAppBar {}
}