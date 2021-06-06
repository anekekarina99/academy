
package com.dicoding.motive.ui.favor.m

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@Suppress("DEPRECATION")
@RunWith(MockitoJUnitRunner::class)
class MovieFavorViewModelTest {

    private lateinit var viewModel: MovieFavorViewModel

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var acdRepository = mock(AcademyRepository::class.java)


    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pL: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavorViewModel(acdRepository)
    }


    @Test
    fun getFavorMovie() {
        val dummyCourses = pL
        `when`(dummyCourses.size).thenReturn(10)
        val courses = MutableLiveData<PagedList<MovieEntity>>()
        courses.value = dummyCourses

        `when`(acdRepository.getFavorMovie()).thenReturn(courses)
        val courseEntities = viewModel.getFavorM().value
        verify(acdRepository).getFavorMovie()
        assertNotNull(courseEntities)
        assertEquals(10, courseEntities?.size)

        viewModel.getFavorM().observeForever(observer)
        verify(observer).onChanged(dummyCourses)


    }


}