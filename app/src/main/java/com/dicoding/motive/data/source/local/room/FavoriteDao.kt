package com.dicoding.motive.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity



@Dao
interface FavoriteDao {

    @Query("SELECT * FROM tb_moviefav")
    fun getMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tb_tvfav")
    fun getTv(): LiveData<List<TvEntity>>

    @Query("SELECT * FROM tb_moviefav where isFavorite = 1")
    fun getMovieFavorite():DataSource.Factory<Int, MovieEntity>


    @Query("SELECT * FROM tb_tvfav where isFavorite = 1")
    fun getTvFavorite(): DataSource.Factory<Int, TvEntity>

    @Transaction
    @Query("SELECT * FROM tb_moviefav WHERE id = :id")
    fun getMovieById(id:Int): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tb_tvfav WHERE id = :id")
    fun getTvById(id:Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(entity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(entity: List<TvEntity>)

    @Update
    fun updateMovie(course:MovieEntity)

    @Update
    fun updateTv(course:TvEntity)

}
