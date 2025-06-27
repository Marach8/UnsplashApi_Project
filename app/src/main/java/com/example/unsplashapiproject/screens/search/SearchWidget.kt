package com.example.unsplashapiproject.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SearchWidget(
    hintText: String,
    onSearchClick: (String) -> Unit,
    onInputChanged: (String) -> Unit,
    onClosedIconTapped: () -> Unit,
){
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            //.height(56.dp)
            .padding(top = 20.dp)
            .semantics { contentDescription = "Search Widget" },
        shadowElevation = TopAppBarDefaults.MediumAppBarExpandedHeight
    ){
        TextField(
            value = hintText,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Text field" },
            onValueChange = {onInputChanged(it)},
            placeholder = {
                Text(
                    text = "Search here...",
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    color = Color.White,
                )
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Icon",
                    modifier = Modifier.clickable { onClosedIconTapped() }
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearchClick(hintText)
                }
            )
        )
    }
}

@Composable
@Preview
fun PPreview(){
    SearchWidget(
        hintText ="",
        onSearchClick = {},
        onInputChanged = {},
        onClosedIconTapped = {}
    )
}