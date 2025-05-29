package com.example.unsplashapiproject.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.unsplashapiproject.data.local.UnsplashDatabase
import com.example.unsplashapiproject.data.models.UnsplashImage
import com.example.unsplashapiproject.data.pagin.UnsplashRemoteMediator
import com.example.unsplashapiproject.data.remote.UnsplashApi
import com.example.unsplashapiproject.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
){
    @OptIn(ExperimentalPagingApi::class)
    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = {unsplashDatabase.unsplashImageDao().getAllImages()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashDatabase = unsplashDatabase,
                unsplashApi = unsplashApi
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}