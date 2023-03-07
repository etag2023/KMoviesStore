package com.cmaye.kmoviesstore.ui_helper.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cmaye.kmoviesstore.model.*
import com.cmaye.kmoviesstore.network.ApiInterface
import com.cmaye.kmoviesstore.network.RetrofitClient
import retrofit2.Retrofit
import javax.inject.Inject

class MoviesViewModel @Inject constructor(

) : ViewModel() {

    var retrofit: Retrofit = RetrofitClient.getInstance()
    var apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

    suspend fun getPopularMoviesList(apiKey: String): PopularMovies? {
        try {


            var result = apiInterface.getPopularMoviesLists(apiKey)
            Log.d("AADD", result.toString())
            repeat(1) {
                result.myList.addAll(result.results!!)
            }

            return result

        } catch (ex: Exception) {
            Log.e("EXC", ex.toString())
            return null
        }
    }
    suspend fun getUpComingMoviesList(apiKey: String): UpComingMovies? {
        try {
            var result = apiInterface.getUpComingMoviesLists(apiKey)
            Log.d("AADD", retrofit.baseUrl().toString())


            return result
        } catch (ex: Exception) {
            Log.e("EXC", ex.toString())
            return null
        }
    }


    suspend fun getMovieDetails(moviesID : Int,apiKey: String) : MovieDetails?
    {
        try {
            var result = apiInterface.getMovieDetails(moviesID,apiKey)
            Log.d("DETAIL", result.toString())
            return result
        } catch (ex: Exception) {
            Log.e("EXC", ex.toString())
            return null
        }
    }



    suspend fun getSimilarMovies(moviesID : Int,apiKey: String) : SimilarMovies?
    {
        try {
            var result = apiInterface.getSimilarMovies(moviesID,apiKey)
            Log.d("DETAIL", result.toString())
            return result
        } catch (ex: Exception) {
            Log.e("EXC", ex.toString())
            return null
        }
    }

    suspend fun getMoviesCredits(moviesID : Int,apiKey: String) : Credits?
    {
        try {
            var result = apiInterface.getMoviesCredits(moviesID,apiKey)
            Log.d("DETAIL", result.toString())
            return result
        } catch (ex: Exception) {
            Log.e("EXC", ex.toString())
            return null
        }
    }












    suspend fun getMoviesList(apiKey: String): MovieList? {
        try {
            var result = apiInterface.getMoviesList("500", apiKey)
            Log.d("AADD", result.toString())
            return result

        } catch (ex: Exception) {
            Log.e("EXC", ex.toString())
            return null
        }
    }

    suspend fun getMoviesImage(moviesID: Int, apiKey: String): GetImages? {
        try {
            var result = apiInterface.getMoviesImage(moviesID, apiKey)
            Log.e("IMG", result.toString())
            return result

        } catch (ex: Exception) {
            Log.e("EXC", ex.toString())
            return null
        }
    }
}