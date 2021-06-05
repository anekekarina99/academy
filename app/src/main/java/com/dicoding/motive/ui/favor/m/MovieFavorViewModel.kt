@file:Suppress("DEPRECATION")

package com.dicoding.motive.ui.favor.m

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity


class MovieFavorViewModel (private val a: AcademyRepository) : ViewModel() {
    fun getFavorM(): LiveData<PagedList<MovieEntity>> = a.getFavorMovie()

    fun setFavorM(courseEntity: MovieEntity) {
        val newState = !courseEntity.isFavorite
        a.setFavoriteM(courseEntity, newState)
    }
}