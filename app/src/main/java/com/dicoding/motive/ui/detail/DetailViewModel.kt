package com.dicoding.motive.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.vo.Resource

class DetailViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private var mId = MutableLiveData<Int>()


    fun setMovieId(mId: Int) {
        this.mId.value = mId
    }

    var getMovieDetail: LiveData<Resource<MovieEntity>> = Transformations.switchMap(mId) {
        academyRepository.getMovieDetail(it)
    }

    fun setMovie(){
        val favRes = getMovieDetail.value
        if(favRes != null){
                val mFav = favRes.data

            if(mFav != null){
                val s = !mFav.isFavorite
                academyRepository.setMovie(mFav,s)
            }

        }
    }

}


