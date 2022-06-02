package com.brainstem.movies.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brainstem.movies.repository.database.dao.LatestMovieDao
import com.brainstem.movies.repository.database.dao.PopularMovieDao
import com.brainstem.movies.repository.database.dao.UpcomingMovieDao
import com.brainstem.movies.models.latest_movies.LatestMovie
import com.brainstem.movies.models.popular_movies.Result

@Database(entities = [Result::class, LatestMovie::class, com.brainstem.movies.models.upcoming_movies.Result::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract  fun popularMovieDao(): PopularMovieDao
    abstract fun latestMovieDao(): LatestMovieDao
    abstract fun upcomingMovieDao(): UpcomingMovieDao
}