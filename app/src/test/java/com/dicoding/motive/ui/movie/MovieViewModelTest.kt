package com.dicoding.motive.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.vo.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(academyRepository)
    }

    @Test
    fun getMovie() {
        val dummyCourses = Resource.success(pagedList)
        `when`(dummyCourses.data?.size).thenReturn(10)
        val courses = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        courses.value = dummyCourses

        `when`(academyRepository.getMoviePopular()).thenReturn(courses)
        val courseEntities = viewModel.getMovie().value?.data
        verify(academyRepository).getMoviePopular()
        assertNotNull(courseEntities)
        assertEquals(10, courseEntities?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}