package com.brainstem.movies.repository.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.brainstem.movies.models.latest_movies.LatestMovie
import com.brainstem.movies.repository.database.MovieDatabase
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LatestMovieDaoTest {
    @get:Rule
    val instantTaskTaskExecutorRule = InstantTaskExecutorRule()
    private  lateinit var database: MovieDatabase
    private lateinit var latestMovieDao: LatestMovieDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
        latestMovieDao = database.latestMovieDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertLatestMovie() = runBlockingTest {
        val latestMovie = LatestMovie(true, null, 1, "en",
            "ercuyugbiutreserfgyubilmikuy", 11111.1, null,
            "2022-03-15", "My added movie", 8.0)

        latestMovieDao.insertLatestMovie(latestMovie)
        val allLatestMovies = latestMovieDao.getLatestMovie().getOrAwaitValue()
        Truth.assertThat(allLatestMovies).contains(latestMovie)
    }
}