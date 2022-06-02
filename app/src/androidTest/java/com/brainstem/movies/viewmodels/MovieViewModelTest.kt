package com.brainstem.movies.viewmodels

import com.brainstem.movies.BuildConfig.API_KEY
import com.brainstem.movies.repository.Repository
import com.brainstem.movies.models.latest_movies.LatestMovie
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{
    private lateinit var movieViewModel: MovieViewModel

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(repository)
    }

    val latestMovie = LatestMovie(null, null, 1, null,
        null, 0.0, null, null, null, null)

    @Test
    fun getLatestMovieFromApiToDbTest(){
        val response = movieViewModel.getLatestMovieFromApiToDb(API_KEY)
        assertEquals(latestMovie, response)
    }
}