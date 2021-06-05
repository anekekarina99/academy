@file:Suppress("DEPRECATION")

package com.dicoding.motive.ui.favor.t

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.TvEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvFavorViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var pL: PagedList<TvEntity>

    @Mock
    private var acdRepository = Mockito.mock(AcademyRepository::class.java)

    private lateinit var viewModel: TvFavorViewModel

    @Before
    fun setUp() {
        viewModel = TvFavorViewModel(acdRepository)
    }


    @Test
    fun getFavorTv() {
        val dummyFavoriteMovie = pL
        Mockito.`when`(dummyFavoriteMovie.size).thenReturn(10)
        val favoriteMovie = MutableLiveData<PagedList<TvEntity>>()
        favoriteMovie.value = dummyFavoriteMovie

        Mockito.`when`(acdRepository.getFavorTv()).thenReturn(favoriteMovie)
        val movieEntities = viewModel.getFavorT().value
        Mockito.verify(acdRepository).getFavorTv()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getFavorT().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyFavoriteMovie)
    }
}