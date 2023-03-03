package com.cmaye.kmoviesstore.ui_helper.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cmaye.kmoviesstore.model.MovieList
import com.cmaye.kmoviesstore.model.PopularMovies
import com.cmaye.kmoviesstore.model.UpComingMovies
import com.cmaye.kmoviesstore.network.ApiInterface
import com.cmaye.kmoviesstore.network.RetrofitClient
import retrofit2.Retrofit
import javax.inject.Inject

class MoviesViewModel @Inject constructor(

) : ViewModel(){

    var retrofit: Retrofit = RetrofitClient.getInstance()
    var apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

    suspend fun getPopularMoviesList(apiKey: String) : PopularMovies?
    {
        try {
            var result = apiInterface.getPopularMoviesLists(apiKey)
            Log.d("AADD",result.toString())
            return result

        }catch (ex : Exception)
        {
            Log.e("EXC",ex.toString())
            return null
        }
    }


    suspend fun getUpComingMoviesList(apiKey : String) : UpComingMovies?
    {
        try {
            var result = apiInterface.getUpComingMoviesLists(apiKey)
            Log.d("AADD",retrofit.baseUrl().toString())


            return result
        }catch (ex : Exception)
        {
            Log.e("EXC",ex.toString())
            return null
        }
    }

    suspend fun getMoviesList(apiKey : String) : MovieList?
    {
        try {
            var result = apiInterface.getMoviesList("500",apiKey)
            Log.d("AADD",result.toString())
            return result

        }catch (ex : Exception)
        {
            Log.e("EXC",ex.toString())
            return null
        }
    }
}