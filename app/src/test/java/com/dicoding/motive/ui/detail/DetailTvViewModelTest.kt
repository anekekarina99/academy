package com.dicoding.motive.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.utils.DataDummy
import com.dicoding.motive.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel

    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val tId = dummyTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var academyRepository= Mockito.mock(AcademyRepository::class.java)


    @Mock
    private lateinit var tvObserver: Observer<Resource<TvEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(academyRepository)
        viewModel.setTvId(tId)

    }

    @Test
    fun getTvDetail() {
        val dummyT = Resource.success(DataDummy.generateDummyTvDetail(tId))
        val course = MutableLiveData<Resource<TvEntity>>()
        course.value = dummyT

        Mockito.`when`(academyRepository.getTvDetail(tId)).thenReturn(course)
        viewModel.getTvDetail.observeForever(tvObserver)
        Mockito.verify(academyRepository).getTvDetail(tId)
        Assert.assertNotNull(viewModel.getTvDetail)
        Assert.assertEquals(viewModel.getTvDetail.value?.data?.id, dummyTv.id )


    }

    @Test
    fun setFavorite(){
        val dummyMf = Resource.success(DataDummy.generateDummyTv()[0])
        val m = MutableLiveData<Resource<TvEntity>>()
        m.value = dummyMf

        Mockito.`when`(academyRepository.getTvDetail(tId)).thenReturn(m)
        viewModel.getTvDetail.observeForever(tvObserver)
        verify(tvObserver).onChanged(m.value)
        viewModel.setTv()
        verify(academyRepository).setFavoriteT(m.value?.data as TvEntity, true)
    }


}