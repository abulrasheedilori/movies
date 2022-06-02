package com.brainstem.movies.models.upcoming_movies


import com.brainstem.movies.models.upcoming_movies.Dates
import com.brainstem.movies.models.upcoming_movies.Result
import com.google.gson.annotations.SerializedName

data class UpcomingMoviesModel(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)