package com.dicoding.motive.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.vo.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = TvViewModel(academyRepository)
    }

    @Test
    fun getTv() {
        val dummyCourses = Resource.success(pagedList)
        `when`(dummyCourses.data?.size).thenReturn(10)
        val courses = MutableLiveData<Resource<PagedList<TvEntity>>>()
        courses.value = dummyCourses

        `when`(academyRepository.getTvPopular()).thenReturn(courses)
        val courseEntities = viewModel.getTv().value?.data
        verify(academyRepository).getTvPopular()
        TestCase.assertNotNull(courseEntities)
        TestCase.assertEquals(10, courseEntities?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}