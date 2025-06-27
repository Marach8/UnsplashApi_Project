package com.example.unsplashapiproject.data.remote

import com.example.unsplashapiproject.BuildConfig
import com.example.unsplashapiproject.data.models.UnsplashImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UnsplashApi {
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ) : List<UnsplashImage>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
    ) : SearchResult
}



@Serializable
data class SearchResult(
    @SerialName("results")
    val images: List<UnsplashImage>
)