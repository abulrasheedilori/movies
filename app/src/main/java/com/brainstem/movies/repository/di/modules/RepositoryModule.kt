package com.brainstem.movies.repository.di.modules

import com.brainstem.movies.repository.Repository
import com.brainstem.movies.repository.database.dao.LatestMovieDao
import com.brainstem.movies.repository.database.dao.PopularMovieDao
import com.brainstem.movies.repository.database.dao.UpcomingMovieDao
import com.brainstem.movies.repository.remote.GetMovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule{

    @Singleton
    @Provides

    fun provideRepository(
        getMovieApi: GetMovieApi,
        latestMovieDao: LatestMovieDao,
        popularMovieDao: PopularMovieDao,
        upcomingMovieDao: UpcomingMovieDao
    ): Repository = Repository(getMovieApi, latestMovieDao, popularMovieDao, upcomingMovieDao)
}