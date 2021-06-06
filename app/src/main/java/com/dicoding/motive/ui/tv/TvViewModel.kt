package com.dicoding.motive.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.vo.Resource

class TvViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getTv(): LiveData<Resource<PagedList<TvEntity>>> = academyRepository.getTvPopular()
}

