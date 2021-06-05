@file:Suppress("DEPRECATION")

package com.dicoding.motive.ui.favor.t

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.TvEntity

class TvFavorViewModel(private val a: AcademyRepository) : ViewModel() {
    fun getFavorT(): LiveData<PagedList<TvEntity>> = a.getFavorTv()

    fun setFavorT(courseEntity: TvEntity) {
        val newState = !courseEntity.isFavorite
        a.setFavoriteT(courseEntity, newState)
    }
}