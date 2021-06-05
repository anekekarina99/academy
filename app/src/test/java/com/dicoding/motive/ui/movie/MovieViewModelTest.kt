package com.dicoding.motive.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.utils.DataDummy
import com.dicoding.motive.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(academyRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(DataDummy.generateDummyMovie())
        val courses = MutableLiveData<Resource<List<MovieEntity>>>()
        courses.value = dummyMovie

        Mockito.`when`(academyRepository.getMoviePopular()).thenReturn(courses)
        val courseEntities = viewModel.getMovie().value?.data
        Mockito.verify(academyRepository).getMoviePopular()
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(dummyMovie.data?.size, courseEntities?.size)

        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}