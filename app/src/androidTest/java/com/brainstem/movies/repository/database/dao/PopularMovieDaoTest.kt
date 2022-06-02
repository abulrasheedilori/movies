import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.brainstem.movies.models.popular_movies.Result
import com.brainstem.movies.repository.database.MovieDatabase
import com.brainstem.movies.repository.database.dao.PopularMovieDao
import com.brainstem.movies.repository.database.dao.getOrAwaitValue
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
class PopularMovieDaoTest {
    @get:Rule
    val instantTaskTaskExecutorRule = InstantTaskExecutorRule()
    private  lateinit var database: MovieDatabase
    private lateinit var popularMovieDao: PopularMovieDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
        popularMovieDao = database.popularMovieDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertLatestMovie() = runBlockingTest {
        val popularMovie = Result(true, null, 1, "en",
            "oikmnuygbytfc tcxeseazwscrubyiu", 11111.1, null,
            "2022-03-15", "My added movie", 8.0)
        val listOfPopularMovies = listOf(popularMovie)

        popularMovieDao.insertAllPopularMovies(listOfPopularMovies)
        val allPopularMovies = popularMovieDao.getAllPopularMovieOrderByTitle().getOrAwaitValue()
        Truth.assertThat(allPopularMovies).isEqualTo(listOfPopularMovies)
    }
}