package com.example.unsplashapiproject.screens.commons

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.example.unsplashapiproject.data.models.UnsplashImage


@ExperimentalCoilApi
@Composable
fun RenderUnsplashImage(image: UnsplashImage){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                val unsplashUserUrl = "https://unsplash.com/@${image.user.name}?utm_source=Android App&utm_medium=referral"

                //Log.d("UnsplashClick", "Opening URL: $unsplashUserUrl")
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(unsplashUserUrl)
                )
                context.startActivity(browserIntent)
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ){
        AsyncImage(
            model = image.urls?.regular,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            fallback = rememberVectorPainter(Icons.Outlined.AccountBox),
            placeholder = rememberVectorPainter(Icons.Outlined.Refresh)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .alpha(ContentAlpha.medium),
            color = Color.Black,
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = buildAnnotatedString {
                    append("Photos by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                        append(image.user.name)
                    }
                    append(" on Unsplash")
                },
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            LikesCounter(likes = image.likes.toString())
        }
    }
}


@Composable
fun LikesCounter(
    likes: String,
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ){
        Icon(
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = "Like Icon",
            tint = Color.Red
        )
        HorizontalDivider(modifier = Modifier.width(6.dp))
        Text(
            text = likes,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}