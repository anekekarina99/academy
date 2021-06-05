@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.dicoding.motive.data.source.remote


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.motive.api.RetrofitClient
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.data.source.remote.response.DetailMovieResponse
import com.dicoding.motive.data.source.remote.response.DetailTvResponse
import com.dicoding.motive.data.source.remote.response.MovieResponse
import com.dicoding.motive.data.source.remote.response.TvResponse
import com.dicoding.motive.utils.Constant.Companion.API_KEY
import com.dicoding.motive.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource  {



    companion object {

        private const val TAG = "This Remote Data Source"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource()
                }
    }

    fun getMoviePopular(): LiveData<ApiResponse<List<DetailMovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<DetailMovieResponse>>>()
        RetrofitClient.getApiService()
                .getMoviePopular(API_KEY, 1)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                       resultMovieResponse.value = ApiResponse.success(response.body()?.results as List<DetailMovieResponse>)
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })

        return resultMovieResponse
    }

    fun getTvPopular():LiveData<ApiResponse<List<DetailTvResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvResponse = MutableLiveData<ApiResponse<List<DetailTvResponse>>>()
        RetrofitClient.getApiService()
                .getTvList(API_KEY, 1)
                .enqueue(object : Callback<TvResponse> {
                    override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                        resultTvResponse.value = ApiResponse.success(response.body()?.results as List<DetailTvResponse>)
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })
        return resultTvResponse
    }

    fun getMovieDetails(id: Int) : LiveData<ApiResponse<DetailMovieResponse>>{
        EspressoIdlingResource.increment()
        val resultDetailM = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        RetrofitClient.getApiService()
                .getMovieDetails(id, API_KEY)
                .enqueue(object : Callback<MovieEntity>{
                    override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                       resultDetailM.value = ApiResponse.success(response.body() as DetailMovieResponse)
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })
        return resultDetailM
    }

    fun getTvDetails(id: Int): LiveData<ApiResponse<DetailTvResponse>>{
        EspressoIdlingResource.increment()
        val resultDetailTv = MutableLiveData<ApiResponse<DetailTvResponse>>()
        RetrofitClient.getApiService()
                .getTvDetails(id, API_KEY)
                .enqueue(object : Callback<TvEntity>{
                    override fun onResponse(call: Call<TvEntity>, response: Response<TvEntity>) {
                        resultDetailTv.value = ApiResponse.success(response.body() as DetailTvResponse)
                        Log.d(TAG, response.code().toString())
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<TvEntity>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                        EspressoIdlingResource.decrement()
                    }
                })

        return resultDetailTv
    }


}

