package com.dicoding.motive.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.data.source.local.room.FavoriteDao

class LocalDataSource private constructor(private val mFavoriteDao: FavoriteDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao).apply { INSTANCE = this }
    }

    fun getMovieAll(): DataSource.Factory<Int, MovieEntity> = mFavoriteDao.getMovie()

    fun getTvAll(): DataSource.Factory<Int, TvEntity> = mFavoriteDao.getTv()

    fun getMovieFavorite(): DataSource.Factory<Int, MovieEntity> = mFavoriteDao.getMovieFavorite()

    fun getTvFavorite(): DataSource.Factory<Int, TvEntity> = mFavoriteDao.getTvFavorite()

    fun getMovieById(id: Int): LiveData<MovieEntity> =
        mFavoriteDao.getMovieById(id)

    fun getTvById(id: Int): LiveData<TvEntity> =
        mFavoriteDao.getTvById(id)

    fun insertMovie(entity: List<MovieEntity>) = mFavoriteDao.insertMovie(entity)

    fun insertTv(entity: List<TvEntity>) = mFavoriteDao.insertTv(entity)


    fun setMovie(course: MovieEntity, newState: Boolean) {
        course.isFavorite = newState
        mFavoriteDao.updateMovie(course)
    }

    fun setTv(course: TvEntity, newState: Boolean) {
        course.isFavorite = newState
        mFavoriteDao.updateTv(course)
    }

}