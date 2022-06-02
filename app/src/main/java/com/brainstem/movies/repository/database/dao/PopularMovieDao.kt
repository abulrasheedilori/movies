package com.brainstem.movies.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brainstem.movies.models.popular_movies.Result

@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM popular_movie")
    fun getAllPopularMovieOrderByTitle(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPopularMovies(popularMovies: List<Result>)

    @Query("SELECT * FROM popular_movie WHERE id =:id")
    fun getPopularMovieById(id: Int) : LiveData<Result>

    }