package com.dicoding.motive.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.motive.data.AcademyRepository
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.utils.DataDummy
import com.dicoding.motive.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    private lateinit var observer: Observer<Resource<List<TvEntity>>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(academyRepository)
    }

    @Test
    fun getTv() {
        val dummyTv = Resource.success(DataDummy.generateDummyTv())
        val courses = MutableLiveData<Resource<List<TvEntity>>>()
        courses.value = dummyTv

        `when`(academyRepository.getTvPopular()).thenReturn(courses)
        val courseEntities = viewModel.getTv().value?.data
        verify(academyRepository).getTvPopular()
        assertNotNull(courseEntities)
        assertEquals(dummyTv.data?.size, courseEntities?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}