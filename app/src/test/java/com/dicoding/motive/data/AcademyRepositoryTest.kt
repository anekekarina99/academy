package com.dicoding.motive.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.motive.data.source.local.LocalDataSource
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.data.source.remote.RemoteDataSource
import com.dicoding.motive.utils.AppExecutors
import com.dicoding.motive.utils.DataDummy
import com.dicoding.motive.utils.LiveDataTestUtil
import com.dicoding.motive.utils.PagedListUtil
import com.dicoding.motive.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val academyRepository = FakeAcademyRepository(remote, local, appExecutors)

    private val dummyMovieData = DataDummy.generateDummyMovie()
    private val movieId = dummyMovieData[0].id
    private val dummyTvData = DataDummy.generateDummyTv()
    private val tvId = dummyTvData[0].id





    @Test
    fun getMoviePopular() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovieAll()).thenReturn(dataSourceFactory)
        academyRepository.getMoviePopular()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getMovieAll()
        assertNotNull(courseEntities.data)
        assertEquals(dummyMovieData.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail(){
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyDetailMovie(DataDummy.generateDummyMovie()[0].id)
        `when`(local.getMovieById(movieId)).thenReturn(dummyEntity)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getMovieDetail(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(courseEntities.data)
        assertNotNull(courseEntities.data?.originalTitle)
    }

    @Test
    fun getTvPopular(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getTvAll()).thenReturn(dataSourceFactory)
        academyRepository.getTvPopular()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()))
        verify(local).getTvAll()
        assertNotNull(courseEntities.data)
        assertEquals(dummyTvData.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getTvDetail(){
        val dummyEntity = MutableLiveData<TvEntity>()
        dummyEntity.value = DataDummy.generateDummyTvDetail(DataDummy.generateDummyTv()[0].id)
        `when`(local.getTvById(tvId)).thenReturn(dummyEntity)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getTvDetail(tvId))
        verify(local).getTvById(tvId)
        assertNotNull(courseEntities.data)
        assertNotNull(courseEntities.data?.originalName)
    }

    @Test
    fun getFavorMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovieFavorite()).thenReturn(dataSourceFactory)
        academyRepository.getFavorMovie()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getMovieFavorite()
        assertNotNull(courseEntities)
        assertEquals(dummyMovieData.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getFavorTv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getTvFavorite()).thenReturn(dataSourceFactory)
        academyRepository.getFavorTv()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()))
        verify(local).getTvFavorite()
        assertNotNull(courseEntities)
        assertEquals(dummyTvData.size.toLong(), courseEntities.data?.size?.toLong())
    }



}