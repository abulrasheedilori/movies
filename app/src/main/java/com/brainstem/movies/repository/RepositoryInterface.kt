package com.brainstem.movies.repository

import androidx.lifecycle.LiveData
import com.brainstem.movies.models.latest_movies.LatestMovie
import com.brainstem.movies.models.popular_movies.PopularMovieModels
import com.brainstem.movies.models.upcoming_movies.UpcomingMoviesModel

interface RepositoryInterface {

    suspend fun getPopularMovieApi(apiKey: String, page: Int): PopularMovieModels
    suspend fun getLatestMovieApi(apiKey: String): LatestMovie
    suspend fun getUpcomingMovieApi(apiKey: String, page: Int): UpcomingMoviesModel

    suspend fun  insertPopularMoviesToMovieDb(popularMovies: List<com.brainstem.movies.models.popular_movies.Result>)
    fun  getPopularMoviesFromAMovieDb(): LiveData<List<com.brainstem.movies.models.popular_movies.Result>>
    suspend fun getPopularMovieFromDbById(id: Int): LiveData<com.brainstem.movies.models.popular_movies.Result>

    suspend fun  insertLatestMoviesToMovieDb(latestMovie: LatestMovie)
    fun  getLatestMoviesFromMovieDb(): LiveData<List<LatestMovie>>
    suspend fun getLatestMovieFromDbById(id: Int): LiveData<LatestMovie>

    suspend fun  insertUpcomingMoviesToMovieDb(
        upcomingMovies: List<com.brainstem.movies.models.upcoming_movies.Result>)
    fun  getUpcomingMoviesFromMovieDb()
        : LiveData<List<com.brainstem.movies.models.upcoming_movies.Result>>

    fun getUpcomingMovieFromDbById(
        id: Int
    ): LiveData<com.brainstem.movies.models.upcoming_movies.Result>
}