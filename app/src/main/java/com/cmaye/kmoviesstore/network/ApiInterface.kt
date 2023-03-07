package com.cmaye.kmoviesstore.network

import com.cmaye.kmoviesstore.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMoviesLists(
        @Query("api_key") apiKey: String
    ) : PopularMovies



    @GET("movie/upcoming")
    suspend fun getUpComingMoviesLists(
        @Query("api_key") apiKey: String,
    ) : UpComingMovies

    //https://api.themoviedb.org/3/movie/500?api_key=1d3080eac4ced11d76ad16ecd5a3e536
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieDetails



    // Actors & Actress
//    https://api.themoviedb.org/3/movie/500/credits?api_key=1d3080eac4ced11d76ad16ecd5a3e536&language=en-US
    @GET("movie/{movie_id}/credits")
    suspend fun getMoviesCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Credits


    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String

    ) : SimilarMovies







    //    https://api.themoviedb.org/3/movie/500/lists?api_key=1d3080eac4ced11d76ad16ecd5a3e536
    @GET("movie/{movieId}/lists")
    suspend fun getMoviesList(
        @Path("movieId") movieId: String, @Query("api_key") apiKey: String
    ): MovieList



    @GET("/movie/{movieId}/images")
    suspend fun getMoviesImage(
        @Path("movieId") movieId : Int,@Query("api_key") apiKey: String
    ) : GetImages

}