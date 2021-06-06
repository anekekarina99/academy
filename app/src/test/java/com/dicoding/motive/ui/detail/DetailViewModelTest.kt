package com.dicoding.motive.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.utils.DataDummy
import com.dicoding.motive.vo.Resource
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyTv = DataDummy.generateDummyMovie()[0]
    private val tId = dummyTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var academyRepository = Mockito.mock(AcademyRepository::class.java)


    @Mock
    private lateinit var tvObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(academyRepository)
        viewModel.setMovieId(tId)

    }

    @Test
    fun getMovieDetail() {
        val dummyT = Resource.success(DataDummy.generateDummyDetailMovie(tId))
        val course = MutableLiveData<Resource<MovieEntity>>()
        course.value = dummyT

        Mockito.`when`(academyRepository.getMovieDetail(tId)).thenReturn(course)
        viewModel.getMovieDetail.observeForever(tvObserver)
        Mockito.verify(academyRepository).getMovieDetail(tId)
        Assert.assertNotNull(viewModel.getMovieDetail)
        Assert.assertEquals(viewModel.getMovieDetail.value?.data?.id, dummyTv.id)


    }

    @Test
    fun setFavorite(){
        val dummyMf = Resource.success(DataDummy.generateDummyMovie()[0])
        val m = MutableLiveData<Resource<MovieEntity>>()
        m.value = dummyMf

        Mockito.`when`(academyRepository.getMovieDetail(tId)).thenReturn(m)
        viewModel.setMovie()

        viewModel.getMovieDetail.observeForever(tvObserver)
        verify(tvObserver).onChanged(m.value)

        val expectedValue = m.value
        val actualValue = viewModel.getMovieDetail.value

        TestCase.assertEquals(expectedValue, actualValue)
    }

}