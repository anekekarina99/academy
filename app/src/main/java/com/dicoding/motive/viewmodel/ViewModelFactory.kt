package com.dicoding.motive.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.di.Injection
import com.dicoding.motive.ui.detail.DetailTvViewModel
import com.dicoding.motive.ui.detail.DetailViewModel
import com.dicoding.motive.ui.favor.m.MovieFavorViewModel
import com.dicoding.motive.ui.favor.t.TvFavorViewModel
import com.dicoding.motive.ui.movie.MovieViewModel
import com.dicoding.motive.ui.tv.TvViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                DetailTvViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(TvFavorViewModel::class.java) -> {
                TvFavorViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavorViewModel::class.java) -> {
                MovieFavorViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
