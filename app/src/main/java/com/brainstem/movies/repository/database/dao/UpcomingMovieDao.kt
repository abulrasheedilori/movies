package com.brainstem.movies.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brainstem.movies.models.upcoming_movies.Result

@Dao
interface UpcomingMovieDao {

    @Query("SELECT * FROM upcoming_movie")
    fun getUpcomingMovies(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovies(UpcomingMovies: List<Result>)

    @Query("SELECT * FROM latest_movies WHERE id =:id")
    fun getUpcomingMovie(id: Int) : LiveData<Result>

}