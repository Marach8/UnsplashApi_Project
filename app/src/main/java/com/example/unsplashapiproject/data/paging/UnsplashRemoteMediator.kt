package com.example.unsplashapiproject.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.unsplashapiproject.data.local.UnsplashDatabase
import com.example.unsplashapiproject.data.models.UnsplashImage
import com.example.unsplashapiproject.data.models.UnsplashRemoteKeys
import com.example.unsplashapiproject.data.remote.UnsplashApi

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : RemoteMediator<Int, UnsplashImage>() {

    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    private val unsplashRemoteKeysDao = unsplashDatabase.unsplashRemoteKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
        return try{
            val currentPage = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosest2CurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKey4FirstItem(state)
                    val prevPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKey4LastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey != null
                        )
                    nextPage
                }
            }

            val imagesResponse = unsplashApi.getImages(page = currentPage, perPage = 10)
            val endOfPaginationReached = imagesResponse.isEmpty()

            val prevPage = if(currentPage == 1) null else currentPage - 1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1

            unsplashDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeysDao.deleteAllRemoteKeys()
                }

                val keys = imagesResponse.map { unsplashImg ->
                    UnsplashRemoteKeys(
                        id = unsplashImg.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                unsplashRemoteKeysDao.addAllRemoteKeys(keys = keys)
                unsplashImageDao.addImages(images = imagesResponse)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }
        catch (e: Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosest2CurrentPosition(
        state: PagingState<Int, UnsplashImage>
    ) : UnsplashRemoteKeys?{
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKey4FirstItem(
        state: PagingState<Int, UnsplashImage>
    ) : UnsplashRemoteKeys?{
        val firstNonEmptyPage = state.pages.firstOrNull{page -> page.data.isNotEmpty()}
        return firstNonEmptyPage?.data?.firstOrNull()?.let { unsplashImg ->
            unsplashImg.id.let { id -> unsplashRemoteKeysDao.getRemoteKeys(id = id) }
        }
    }

    private suspend fun getRemoteKey4LastItem(
        state: PagingState<Int, UnsplashImage>
    ) : UnsplashRemoteKeys?{
        val lastNonEmptyPage = state.pages.lastOrNull{page -> page.data.isNotEmpty()}
        return lastNonEmptyPage?.data?.lastOrNull()?.let { unsplashImg ->
            unsplashImg.id.let { id -> unsplashRemoteKeysDao.getRemoteKeys(id = id) }
        }
    }
}