package com.brainstem.movies.repository.di.modules.modules

import android.app.Application
import androidx.room.Room
import com.brainstem.movies.repository.database.MovieDatabase
import com.brainstem.movies.repository.database.dao.LatestMovieDao
import com.brainstem.movies.repository.database.dao.PopularMovieDao
import com.brainstem.movies.repository.database.dao.UpcomingMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    @Singleton
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, MovieDatabase::class.java, "movie_db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideLatestMovieDao(db: MovieDatabase): LatestMovieDao = db.latestMovieDao()

    @Singleton
    @Provides
    fun providePopularMovieDao(db: MovieDatabase): PopularMovieDao = db.popularMovieDao()

    @Singleton
    @Provides
    fun provideUpcomingMovieDao(db: MovieDatabase): UpcomingMovieDao = db.upcomingMovieDao()
}