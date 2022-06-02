package com.brainstem.movies.repository

import androidx.lifecycle.LiveData
import com.brainstem.movies.models.latest_movies.LatestMovie
import com.brainstem.movies.models.popular_movies.PopularMovieModels
import com.brainstem.movies.models.popular_movies.Result
import com.brainstem.movies.models.upcoming_movies.UpcomingMoviesModel
import com.brainstem.movies.repository.RepositoryInterface
import com.brainstem.movies.repository.remote.GetMovieApi
import com.brainstem.movies.repository.database.dao.LatestMovieDao
import com.brainstem.movies.repository.database.dao.PopularMovieDao
import com.brainstem.movies.repository.database.dao.UpcomingMovieDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val getMovieApi: GetMovieApi,
    private val latestMovieDao: LatestMovieDao,
    private val popularMovieDao: PopularMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao
): RepositoryInterface {
    override suspend fun getPopularMovieApi(
        apiKey: String,
        page: Int
    ): PopularMovieModels = getMovieApi.getPopularMovieApi(apiKey, page)

    override suspend fun getLatestMovieApi(
        apiKey: String
    ): LatestMovie = getMovieApi.getLatestMovieApi(apiKey)

    override suspend fun getUpcomingMovieApi(
        apiKey: String,
        page: Int
    ): UpcomingMoviesModel = getMovieApi.getUpcomingMovieApi(apiKey, page)

    override suspend fun insertPopularMoviesToMovieDb(popularMovies: List<Result>){
        return popularMovieDao.insertAllPopularMovies(popularMovies)
    }

    override fun getPopularMoviesFromAMovieDb(): LiveData<List<Result>> {
        return popularMovieDao.getAllPopularMovieOrderByTitle()
    }

    override suspend fun getPopularMovieFromDbById(id: Int): LiveData<Result> {
        return popularMovieDao.getPopularMovieById(id)
    }

    override suspend fun insertLatestMoviesToMovieDb(latestMovie: LatestMovie) {
        return latestMovieDao.insertLatestMovie(latestMovie)
    }

    override fun getLatestMoviesFromMovieDb(): LiveData<List<LatestMovie>> {
        return latestMovieDao.getLatestMovie()
    }

    override suspend fun getLatestMovieFromDbById(id: Int): LiveData<LatestMovie> {
        return latestMovieDao.getLatestMovieById(id)
    }

    override suspend fun insertUpcomingMoviesToMovieDb(
        upcomingMovies: List<com.brainstem.movies.models.upcoming_movies.Result>){
        return upcomingMovieDao.insertUpcomingMovies(upcomingMovies)
    }

    override fun getUpcomingMoviesFromMovieDb()
    : LiveData<List<com.brainstem.movies.models.upcoming_movies.Result>> {
        return upcomingMovieDao.getUpcomingMovies()
    }

    override fun getUpcomingMovieFromDbById(id: Int): LiveData<com.brainstem.movies.models.upcoming_movies.Result> {
        return upcomingMovieDao.getUpcomingMovie(id)
    }
}