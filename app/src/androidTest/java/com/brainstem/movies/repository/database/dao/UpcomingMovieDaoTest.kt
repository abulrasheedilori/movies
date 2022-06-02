import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.brainstem.movies.repository.database.MovieDatabase
import com.brainstem.movies.repository.database.dao.UpcomingMovieDao
import com.brainstem.movies.repository.database.dao.getOrAwaitValue
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
class UpcomingMovieDaoTest {
    @get:Rule
    val instantTaskTaskExecutorRule = InstantTaskExecutorRule()
    private  lateinit var database: MovieDatabase
    private lateinit var upcomingMovieDao: UpcomingMovieDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
        upcomingMovieDao = database.upcomingMovieDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertLatestMovie() = runBlockingTest {
        val upcomingMovie = com.brainstem.movies.models.upcoming_movies.Result(true, null, 1, "en",
            "erdtiyfgb;,mnhlubuyvtcrxzer", 11111.1, null,
            "2022-03-15", "My added movie", 8.0F)
        val listOfUpcomingMovies = listOf(upcomingMovie)

        upcomingMovieDao.insertUpcomingMovies(listOfUpcomingMovies)
        val allUpcomingMovies = upcomingMovieDao.getUpcomingMovies().getOrAwaitValue()
        Truth.assertThat(allUpcomingMovies).isEqualTo(listOfUpcomingMovies)
    }
}