@file:Suppress("DEPRECATION")

package com.dicoding.motive.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.motive.data.source.NetworkBoundResource
import com.dicoding.motive.data.source.local.LocalDataSource
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.data.source.remote.ApiResponse
import com.dicoding.motive.data.source.remote.RemoteDataSource
import com.dicoding.motive.data.source.remote.response.DetailMovieResponse
import com.dicoding.motive.data.source.remote.response.DetailTvResponse
import com.dicoding.motive.utils.AppExecutors
import com.dicoding.motive.vo.Resource

class FakeAcademyRepository(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : AcademyDataSource {

    override fun setFavoriteT(tv: TvEntity, favorite: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setTv(tv, favorite)
        }
    }

    override fun insertT(t: List<TvEntity>) {
        val runnable = {
            if (localDataSource.getTvAll().value.isNullOrEmpty()) {
                localDataSource.insertTv(t)
            }
        }
        appExecutors.diskIO().execute(runnable)
    }

    override fun getFavorTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getTvFavorite(), config).build()
    }

    override fun setFavoriteM(movie: MovieEntity, favorite: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setMovie(movie, favorite) }
    }

    override fun insertM(movie: List<MovieEntity>) {
        val runnable = {
            if (localDataSource.getMovieAll().value.isNullOrEmpty()) {
                localDataSource.insertMovie(movie)
            }
        }
        appExecutors.diskIO().execute(runnable)
    }

    override fun getFavorMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getMovieFavorite(), config).build()
    }

    override fun getMoviePopular(): LiveData<Resource<List<MovieEntity>>> {
        return object :
            NetworkBoundResource<List<MovieEntity>, List<DetailMovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getMovieAll()

            override fun shouldFetch(result: List<MovieEntity>?): Boolean =
                result == null || result.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<DetailMovieResponse>>> =
                remoteDataSource.getMoviePopular()

            public override fun saveCallResult(response: List<DetailMovieResponse>?) {
                val mPost = ArrayList<MovieEntity>()
                if (response != null) {
                    for (res in response) {
                        val m = MovieEntity(
                            id = res.id,
                            posterPath = res.posterPath,
                            originalTitle = res.originalTitle,
                            releaseDate = res.releaseDate,
                            overview = res.overview
                        )
                        mPost.add(m)
                    }
                }

                localDataSource.insertMovie(mPost)
            }
        }.asLiveData()

    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieById(id)
            }

            override fun shouldFetch(result: MovieEntity?): Boolean =
                result == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getMovieDetails(id)

            override fun saveCallResult(response: DetailMovieResponse?) {

            }


        }.asLiveData()
    }

    override fun getTvPopular(): LiveData<Resource<List<TvEntity>>> {
        return object : NetworkBoundResource<List<TvEntity>, List<DetailTvResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvEntity>> =
                localDataSource.getTvAll()

            override fun shouldFetch(result: List<TvEntity>?): Boolean =
                result == null || result.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<DetailTvResponse>>> =
                remoteDataSource.getTvPopular()

            public override fun saveCallResult(response: List<DetailTvResponse>?) {
                val mPost = ArrayList<TvEntity>()
                if (response != null) {
                    for (res in response) {
                        val m = TvEntity(
                            id = res.id,
                            posterPath = res.posterPath,
                            originalName = res.originalName,
                            firstAirDate = res.firstAirDate,
                            overview = res.overview
                        )
                        mPost.add(m)
                    }
                }

                localDataSource.insertTv(mPost)
            }
        }.asLiveData()

    }

    override fun getTvDetail(id: Int): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, DetailTvResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvEntity> {
                return localDataSource.getTvById(id)
            }

            override fun shouldFetch(result: TvEntity?): Boolean =
                result == null

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> =
                remoteDataSource.getTvDetails(id)

            override fun saveCallResult(response: DetailTvResponse?) {

            }


        }.asLiveData()

    }


}


