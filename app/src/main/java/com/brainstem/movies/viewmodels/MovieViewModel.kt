package com.brainstem.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainstem.movies.models.latest_movies.LatestMovie
import com.brainstem.movies.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {

    //create LiveData for our database data
    val latestMovieLiveData: LiveData<List<LatestMovie>> = repository.getLatestMoviesFromMovieDb()
    val popularMovieLiveData = repository.getPopularMoviesFromAMovieDb()
    val upcomingMovieLiveData = repository.getUpcomingMoviesFromMovieDb()

    private var _totalPopularMoviesOnServer: MutableLiveData<Int> = MutableLiveData()
    var totalPopularMoviesOnServer: LiveData<Int> = _totalPopularMoviesOnServer

    private var _totalUpcomingMoviesOnServer: MutableLiveData<Int> = MutableLiveData()
    var totalUpcomingMoviesOnServer: LiveData<Int> = _totalUpcomingMoviesOnServer


    fun getLatestMovieFromApiToDb(apiKey: String) =
        viewModelScope.launch (Dispatchers.IO){
            try {
                val latestMoviesResponse = repository.getLatestMovieApi(apiKey)
                latestMoviesResponse.let { latestMovie ->
                    latestMovie.let { item -> repository.insertLatestMoviesToMovieDb(item)}
                }
            } catch (e: Exception) {
                Log.d("LatestException", e.toString())
        }

    }

    fun getPopularMovieFromApiToDb(apiKey: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO){
            try {
                val popularMoviesResponse = repository.getPopularMovieApi(apiKey, page)
                popularMoviesResponse.let {
                    Log.d("TESTING POPULAR API", it.toString())
                    repository.insertPopularMoviesToMovieDb(it.results)
                    _totalPopularMoviesOnServer.postValue(it.totalPages)
                }
            } catch (e: Exception) {
                Log.d("ERROR_NETWORK_RESPONSE", e.toString())
        }
    }

    fun getUpcomingMovieFromApiToDb(apiKey: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO){
            try {
                val upcomingMoviesResponse = repository.getUpcomingMovieApi(apiKey, page)
                upcomingMoviesResponse.let {
                    repository.insertUpcomingMoviesToMovieDb(it.results)
                    _totalUpcomingMoviesOnServer.postValue(it.totalPages)
                }
            } catch (e: Exception) {
                Log.d("UpcomingException", e.toString())
        }
    }
}