package com.brainstem.movies.repository.remote

import com.brainstem.movies.models.latest_movies.LatestMovie
import com.brainstem.movies.models.popular_movies.PopularMovieModels
import com.brainstem.movies.models.upcoming_movies.UpcomingMoviesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovieApi(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PopularMovieModels

    @GET("movie/latest")
    suspend fun getLatestMovieApi(
        @Query("api_key") apiKey: String
    ): LatestMovie

    @GET("movie/upcoming")
    suspend fun getUpcomingMovieApi(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): UpcomingMoviesModel
}