package com.example.unsplashapiproject.di

import android.content.Context
import androidx.room.Room
import com.example.unsplashapiproject.data.local.UnsplashDatabase
import com.example.unsplashapiproject.utils.Constants.UNSPLASH_DATA_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UnsplashDatabase{
        return Room.databaseBuilder(
            context,
            UnsplashDatabase::class.java,
            UNSPLASH_DATA_BASE
        ).build()
    }
}