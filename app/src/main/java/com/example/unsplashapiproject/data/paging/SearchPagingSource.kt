package com.example.unsplashapiproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplashapiproject.data.models.UnsplashImage
import com.example.unsplashapiproject.data.remote.UnsplashApi


class SearchPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String,
):PagingSource<Int, UnsplashImage>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: 1
        return try{
            val response = unsplashApi.searchImages(query = query, perPage = 10)
            val endOfPaginationReached = response.images.isEmpty()
            if(endOfPaginationReached){
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null,
                )
            }
            else{
                LoadResult.Page(
                    data = response.images,
                    prevKey = if(currentPage == 1) null else currentPage - 1,
                    nextKey = currentPage + 1
                )
            }
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }
}