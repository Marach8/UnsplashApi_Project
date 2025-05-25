package com.example.unsplashapiproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unsplashapiproject.data.local.dao.UnsplashImageDao
import com.example.unsplashapiproject.data.local.dao.UnsplashRemoteKeysDao
import com.example.unsplashapiproject.data.models.UnsplashImage
import com.example.unsplashapiproject.data.models.UnsplashRemoteKeys

@Database(
    entities = [UnsplashImage::class, UnsplashRemoteKeys::class],
    version = 1
)
abstract class UnsplashDatabase: RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}