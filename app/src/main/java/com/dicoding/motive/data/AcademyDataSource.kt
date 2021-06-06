@file:Suppress("DEPRECATION")

package com.dicoding.motive.data


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.vo.Resource

interface AcademyDataSource {

    fun setFavoriteT(tv: TvEntity, favorite:Boolean)

    fun insertT(t : List<TvEntity>)

    fun getFavorTv() : LiveData<PagedList<TvEntity>>

    fun setFavoriteM(movie: MovieEntity, favorite:Boolean)

    fun insertM(movie : List<MovieEntity>)

    fun getFavorMovie() : LiveData<PagedList<MovieEntity>>

    fun getMoviePopular(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieDetail(id : Int): LiveData<Resource<MovieEntity>>

    fun getTvPopular(): LiveData<Resource<PagedList<TvEntity>>>

    fun getTvDetail(id : Int): LiveData<Resource<TvEntity>>


}