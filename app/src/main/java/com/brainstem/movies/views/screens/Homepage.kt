package com.brainstem.movies.views.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brainstem.movies.BuildConfig.API_KEY
import com.brainstem.movies.databinding.HomepageBinding
import com.brainstem.movies.viewmodels.MovieViewModel
import com.brainstem.movies.views.adapters.HomePageAdapter
import com.brainstem.movies.views.adapters.click_listeners.OnMovieClickInterface
import com.google.movies.utils.ObserveNetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Homepage : Fragment(), OnMovieClickInterface {

    private var _binding: HomepageBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomePageAdapter
    private val viewModel: MovieViewModel by viewModels()
    private  lateinit var observeNetwork : ObserveNetworkState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomepageBinding.inflate(inflater, container, false)
        homeAdapter = HomePageAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeNetwork = ObserveNetworkState(requireContext())
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeNetwork.observe(this) {
            when (it) {
                true -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.statusTv.visibility = View.INVISIBLE
                    observeFirstPageFromNetwork()
                    observeFromDatabase()
                    loadMorePopularMoviesOnLastScroll()
                }
                false -> {
                    binding.apply{
                        progressBar.visibility = View.VISIBLE
                        statusTv.visibility = View.VISIBLE
                        observeFromDatabase()
                    }
                }
            }
        }
    }

    private  fun loadMorePopularMoviesOnLastScroll(){
        var page = 2
        val listener = object: RecyclerView.OnScrollListener(){
            @SuppressLint("SetTextI18n")
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                viewModel.totalPopularMoviesOnServer.observe(viewLifecycleOwner) {
                    if (page < it) {
                        page++
                        viewModel.getPopularMovieFromApiToDb(API_KEY, page)
                        observeFromDatabase()
                    }
                }
            }
        }
     binding.homepageRv.addOnScrollListener(listener)
    }

    private fun setUpRecyclerView(){
        binding.homepageRv.apply {
            adapter = homeAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
       }
    }

    private fun observeFromDatabase(){
        viewModel.popularMovieLiveData.observe(viewLifecycleOwner, Observer{
//            Log.d("TESTING DATA FROM DB", it.toString())
            if(!it.isNullOrEmpty()) homeAdapter.setData(it)
            else {
                Toast.makeText(
                    requireContext(),
                    "Your database is empty, Switch on your Internet",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun observeFirstPageFromNetwork(){
        viewModel.getPopularMovieFromApiToDb(API_KEY, 1)
    }

    override fun onClick(position: Int) {
        val popularMovies = homeAdapter.getListOfAllMembersAdapter()
        popularMovies[position].let{
            val action = HomepageDirections.actionHomePageToMovieDetails(
                it,
                null,
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

