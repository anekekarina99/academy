package com.dicoding.motive.ui.favor.m

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavorViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pL: PagedList<MovieEntity>

    @Mock
    private var acdRepository = mock(AcademyRepository::class.java)

    private lateinit var viewModel: MovieFavorViewModel

    @Before
    fun setUp() {
        viewModel = MovieFavorViewModel(acdRepository)
    }


    @Test
    fun getFavorMovie() {
        val dummyFavoriteMovie= pL
        `when`(dummyFavoriteMovie.size).thenReturn(10)
        val favoriteMovie = MutableLiveData<PagedList<MovieEntity>>()
        favoriteMovie.value = dummyFavoriteMovie

        `when`(acdRepository.getFavorMovie()).thenReturn(favoriteMovie)
        val movieEntities = viewModel.getFavorM().value
        verify(acdRepository).getFavorMovie()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getFavorM().observeForever(observer)
        verify(observer).onChanged(dummyFavoriteMovie)
    }


}