package com.brainstem.movies.models.latest_movies


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "latest_movies")
data class LatestMovie(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String?,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
): Parcelable