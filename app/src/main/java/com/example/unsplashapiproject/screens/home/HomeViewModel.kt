package com.example.unsplashapiproject.screens.home

import androidx.lifecycle.ViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.unsplashapiproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@ExperimentalCoilApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
): ViewModel(){
    val getAllImages = repository.getAllImages()
}