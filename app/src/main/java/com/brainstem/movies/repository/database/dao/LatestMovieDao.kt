package com.brainstem.movies.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brainstem.movies.models.latest_movies.LatestMovie

@Dao
interface LatestMovieDao {
    @Query("SELECT * FROM latest_movies")
    fun getLatestMovie(): LiveData<List<LatestMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLatestMovie(latestMovie: LatestMovie)

    @Query("SELECT * FROM popular_movie WHERE id =:id")
    fun getLatestMovieById(id: Int) : LiveData<LatestMovie>
}