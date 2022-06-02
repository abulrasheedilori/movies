package com.brainstem.movies.repository.di.modules.modules

import com.brainstem.movies.repository.database.dao.LatestMovieDao
import com.brainstem.movies.repository.database.dao.PopularMovieDao
import com.brainstem.movies.repository.database.dao.UpcomingMovieDao
import com.brainstem.movies.repository.remote.GetMovieApi
//import com.brainstem.movies.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class RepositoryModule{

//    @Singleton
//    @Provides
    // trying to inject the whole api as a unit to see if it works, I think it should but you never know
//    fun provideRepository(
//        getMovieApi: GetMovieApi,
//        latestMovieDao: LatestMovieDao,
//        popularMovieDao: PopularMovieDao,
//        upcomingMovieDao: UpcomingMovieDao
//    ): Repository = Repository(getMovieApi, latestMovieDao, popularMovieDao, upcomingMovieDao)
//}