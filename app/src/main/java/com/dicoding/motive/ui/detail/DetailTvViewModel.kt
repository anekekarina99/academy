package com.dicoding.motive.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.vo.Resource

class DetailTvViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private var mId = MutableLiveData<Int>()


    fun setTvId(mId: Int) {
        this.mId.value = mId
    }

    var getTvDetail: LiveData<Resource<TvEntity>> = Transformations.switchMap(mId) {
        academyRepository.getTvDetail(it)
    }

    fun setTv(){
        val favRes = getTvDetail.value
        if(favRes != null){
            val mFav = favRes.data

            if(mFav != null){
                val s = !mFav.isFavorite
                academyRepository.setTv(mFav,s)
            }

        }
    }
}