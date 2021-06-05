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
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieTvRepository = FakeAcademyRepository(remote, local, appExecutors)

    private val dummyMovieData = DataDummy.generateDummyMovie()
    private val movieId = dummyMovieData[0].id
    private val dummyTvData = DataDummy.generateDummyTv()
    private val tvId = dummyTvData[0].id
    private val movieDataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
    private val tvDataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>


    @Test
    fun getMoviePopular() {
        val dummyMovie = MutableLiveData<List<MovieEntity>>()
        dummyMovie.value = DataDummy.generateDummyMovie()
        `when`(local.getMovieAll()).thenReturn(dummyMovie)

        val movieEntities = LiveDataTestUtil.getValue(movieTvRepository.getMoviePopular())
        verify(local).getMovieAll()
        assertNotNull(movieEntities.data)
    }

    @Test
    fun getMovieDetail(){
        val dummyDetailMovie = MutableLiveData<MovieEntity>()
        dummyDetailMovie.value = DataDummy.generateDummyDetailMovie(movieId)
        `when`(local.getMovieById(movieId)).thenReturn(dummyDetailMovie)

        val movieDetails = LiveDataTestUtil.getValue(movieTvRepository.getMovieDetail(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieDetails.data)
    }

    @Test
    fun getTvPopular(){
        val dummyTv = MutableLiveData<List<TvEntity>>()
        dummyTv.value = DataDummy.generateDummyTv()
        `when`(local.getTvAll()).thenReturn(dummyTv)

        val tvEntities = LiveDataTestUtil.getValue(movieTvRepository.getTvPopular())
        verify(local).getTvAll()
        assertNotNull(tvEntities)
    }

    @Test
    fun getTvDetail(){
        val dummyDetailTv = MutableLiveData<TvEntity>()
        dummyDetailTv.value = DataDummy.generateDummyTvDetail(tvId)
        `when`(local.getTvById(tvId)).thenReturn(dummyDetailTv)

        val tvDetails = LiveDataTestUtil.getValue(movieTvRepository.getTvDetail(tvId))
        verify(local).getTvById(tvId)
        assertNotNull(tvDetails)
    }

    @Test
    fun getFavorMovie(){
        `when`(local.getMovieFavorite()).thenReturn(movieDataSource)
        movieTvRepository.getFavorMovie()
        verify(local).getMovieFavorite()
    }

    @Test
    fun getFavorTv(){
        `when`(local.getTvFavorite()).thenReturn(tvDataSource)
        movieTvRepository.getFavorTv()
        verify(local).getTvFavorite()
    }



}