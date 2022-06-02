package com.brainstem.movies.views.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.brainstem.movies.BuildConfig.API_KEY
import com.brainstem.movies.databinding.FragmentLatestMovieBinding
import com.brainstem.movies.views.adapters.LatestMovieAdapter
import com.brainstem.movies.views.adapters.click_listeners.OnMovieClickInterface
import com.brainstem.movies.utils.CHECK_NETWORK
import com.brainstem.movies.viewmodels.MovieViewModel
import com.google.movies.utils.ObserveNetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestMovie : Fragment(), OnMovieClickInterface {

    private var _binding: FragmentLatestMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var latestAdapter: LatestMovieAdapter
    private val viewModel: MovieViewModel by viewModels()
    private  lateinit var observeNetwork : ObserveNetworkState


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLatestMovieBinding.inflate(inflater, container, false)
        latestAdapter = LatestMovieAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeNetwork = ObserveNetworkState(requireContext())
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
//        observeLatestMovieFromDatabase()
        observeNetwork.observe(this) {
            when (it) {
                true -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.statusTv.visibility = View.INVISIBLE
                    observeLatestMovieFromDatabase()
                }
                false -> {
                    binding.apply {
                        statusTv.text = CHECK_NETWORK
                        progressBar.visibility = View.VISIBLE
                        statusTv.visibility = View.VISIBLE
                        observeLatestMovieFromDatabase()
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        binding.latestMovieRv.apply {
            adapter = latestAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun observeLatestMovieFromDatabase(){
        viewModel.latestMovieLiveData.observe(viewLifecycleOwner, Observer{
            if(!it.isNullOrEmpty()) latestAdapter.setData(it) else viewModel.getLatestMovieFromApiToDb(
                API_KEY)
        })
    }

    override fun onClick(position: Int) {
        val latestMovies = latestAdapter.getListOfLatestMovieAdapter()
        latestMovies[position].let{
            val action =
                LatestMovieDirections.actionLatestMovieToMovieDetails(
                    null,
                    null,
                    it
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

