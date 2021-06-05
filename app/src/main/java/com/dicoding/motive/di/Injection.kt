package com.dicoding.motive.di

import android.content.Context

import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.LocalDataSource
import com.dicoding.motive.data.source.local.room.FavoriteDatabase
import com.dicoding.motive.data.source.remote.RemoteDataSource
import com.dicoding.motive.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = FavoriteDatabase.getInstance(context)

        val remoteRepository = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteRepository,localDataSource,appExecutors)
    }
}
