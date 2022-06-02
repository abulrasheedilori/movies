package com.brainstem.movies.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.brainstem.movies.utils.IMAGE_URL
import com.brainstem.movies.databinding.MovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsScreen : Fragment() {

    private var _binding: MovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments ?: return

        MovieDetailsScreenArgs.fromBundle(bundle).let { movieDetailsScreenArgs ->
            if (movieDetailsScreenArgs.popularMovie != null) {
                binding.apply {
                    movieDetailsItemLanguageTv.text = movieDetailsScreenArgs.popularMovie?.originalLanguage
                    movieDetailsItemAdultContentTv.text = movieDetailsScreenArgs.popularMovie?.adult.toString()
                    movieDetailsItemPopularityContentTv.text =
                        movieDetailsScreenArgs.popularMovie?.popularity.toString()
                    movieDetailsidContentTv.text = movieDetailsScreenArgs.popularMovie?.id.toString()
                    movieDetailsTitleTv.text = movieDetailsScreenArgs.popularMovie?.title
                    movieDetailsItemVoteAverageContentTv.text =
                        movieDetailsScreenArgs.popularMovie?.voteAverage.toString()
                    movieDetailsOverviewTv.text = movieDetailsScreenArgs.popularMovie?.overview
                    if (movieDetailsScreenArgs.popularMovie?.posterPath != null) {
                        Glide.with(movieDetailsMovieIv.context)
                            .load(IMAGE_URL + movieDetailsScreenArgs.popularMovie!!.posterPath)
                            .into(movieDetailsMovieIv)
                    }
                }
            }
            if (movieDetailsScreenArgs.latestMovie != null) {
                binding.apply {
                    movieDetailsItemLanguageTv.text = movieDetailsScreenArgs.latestMovie?.originalLanguage
                    movieDetailsItemAdultContentTv.text = movieDetailsScreenArgs.latestMovie?.adult.toString()
                    movieDetailsItemPopularityContentTv.text =
                        movieDetailsScreenArgs.latestMovie?.popularity.toString()
                    movieDetailsidContentTv.text = movieDetailsScreenArgs.latestMovie?.id.toString()
                    movieDetailsTitleTv.text = movieDetailsScreenArgs.latestMovie?.title
                    movieDetailsItemVoteAverageContentTv.text =
                        movieDetailsScreenArgs.latestMovie?.voteAverage.toString()
                    movieDetailsOverviewTv.text = movieDetailsScreenArgs.latestMovie?.overview
                    if (movieDetailsScreenArgs.latestMovie?.posterPath != null) {
                        Glide.with(movieDetailsMovieIv.context)
                            .load(IMAGE_URL + movieDetailsScreenArgs.latestMovie!!.posterPath)
                            .into(movieDetailsMovieIv)
                    }
                }
            }
            if (movieDetailsScreenArgs.upcomingMovie != null) {
                binding.apply {
                    movieDetailsItemLanguageTv.text = movieDetailsScreenArgs.upcomingMovie?.originalLanguage
                    movieDetailsItemAdultContentTv.text = movieDetailsScreenArgs.upcomingMovie?.adult.toString()
                    movieDetailsItemPopularityContentTv.text =
                        movieDetailsScreenArgs.upcomingMovie?.popularity.toString()
                    movieDetailsidContentTv.text = movieDetailsScreenArgs.upcomingMovie?.id.toString()
                    movieDetailsTitleTv.text = movieDetailsScreenArgs.upcomingMovie?.title
                    movieDetailsItemVoteAverageContentTv.text =
                        movieDetailsScreenArgs.upcomingMovie?.voteAverage.toString()
                    movieDetailsOverviewTv.text = movieDetailsScreenArgs.upcomingMovie?.overview
                    if (movieDetailsScreenArgs.upcomingMovie?.posterPath != null) {
                        Glide.with(movieDetailsMovieIv.context)
                            .load(IMAGE_URL + movieDetailsScreenArgs.upcomingMovie!!.posterPath)
                            .into(movieDetailsMovieIv)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}