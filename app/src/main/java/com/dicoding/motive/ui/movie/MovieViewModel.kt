package com.dicoding.motive.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.vo.Resource

class MovieViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> = academyRepository.getMoviePopular()
}

