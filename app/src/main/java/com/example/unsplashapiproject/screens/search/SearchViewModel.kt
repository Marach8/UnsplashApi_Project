package com.example.unsplashapiproject.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import coil.annotation.ExperimentalCoilApi
import com.example.unsplashapiproject.data.models.UnsplashImage
import com.example.unsplashapiproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoilApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: Repository
): ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchImages = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchImages = _searchImages

    fun updateSearchQuery(query: String){
        _searchQuery.value = query
    }

    fun searchHeroes(query: String){
        viewModelScope.launch {
            repo.searchImage(query= query).cachedIn(viewModelScope).collect{
                _searchImages.value = it
            }
        }
    }
}