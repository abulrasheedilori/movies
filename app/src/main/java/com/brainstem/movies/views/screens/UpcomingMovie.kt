package com.brainstem.movies.views.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brainstem.movies.BuildConfig.API_KEY
import com.brainstem.movies.databinding.FragmentUpcomingMovieBinding
import com.brainstem.movies.utils.CHECK_NETWORK
import com.brainstem.movies.viewmodels.MovieViewModel
import com.brainstem.movies.views.adapters.UpcomingMovieAdapter
import com.brainstem.movies.views.adapters.click_listeners.OnMovieClickInterface
import com.google.movies.utils.ObserveNetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingMovie : Fragment(), OnMovieClickInterface {

    private var _binding: FragmentUpcomingMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var upcomingAdapter: UpcomingMovieAdapter
    private val viewModel: MovieViewModel by viewModels()
    private  lateinit var observeNetwork : ObserveNetworkState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingMovieBinding.inflate(inflater, container, false)
        upcomingAdapter = UpcomingMovieAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeNetwork = ObserveNetworkState(requireContext())
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeFromDatabase()
        observeNetwork.observe(this) {
            when (it) {
                true -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.statusTv.visibility = View.INVISIBLE
                    observeFirstPageFromNetwork()
                    loadMoreUpcomingMoviesOnLastScroll()
                }
                false -> {
                    binding.apply{
                        statusTv.text = CHECK_NETWORK
                        progressBar.visibility = View.VISIBLE
                        statusTv.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private  fun loadMoreUpcomingMoviesOnLastScroll(){
        var page = 2
        val listener = object: RecyclerView.OnScrollListener(){
            @SuppressLint("SetTextI18n")
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                viewModel.totalPopularMoviesOnServer.observe(viewLifecycleOwner, Observer{
                    if(page < it) {
                        page++
                        viewModel.getPopularMovieFromApiToDb(API_KEY, page)
                        observeFromDatabase()
                    }
                })
            }
        }
        binding.upcomingRv.addOnScrollListener(listener)
    }

    private fun setUpRecyclerView(){
        binding.upcomingRv.apply {
            adapter = upcomingAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun observeFromDatabase(){
        viewModel.upcomingMovieLiveData.observe(viewLifecycleOwner, Observer{
            if(!it.isNullOrEmpty()) upcomingAdapter.setData(it)
            else Toast.makeText(
                requireContext(),
                "Your database is empty, Switch on your Internet!",
                Toast.LENGTH_SHORT).show()
        })
    }
    private fun observeFirstPageFromNetwork(){
        viewModel.getUpcomingMovieFromApiToDb(API_KEY, 1)
    }

    override fun onClick(position: Int) {
        val upcomingMovies = upcomingAdapter.getListOfUpcomingMovieAdapter()
        upcomingMovies[position].let{
            val action =
                UpcomingMovieDirections.actionUpcomingMovieToMovieDetails(
                    null,
                    it,
                    null
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

