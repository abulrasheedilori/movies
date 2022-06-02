package com.brainstem.movies.views.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.brainstem.movies.views.screens.adapters.click_listeners.OnMovieClickInterface
import com.brainstem.movies.utils.IMAGE_URL
import com.brainstem.movies.databinding.MovieRecyclerviewItemBinding

class UpcomingMovieAdapter(
    private val onMovieClickItem: OnMovieClickInterface
): RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingMovieViewHolder>() {
    private var listOfUpcomingMovies: List<com.google.movies.models.upcoming_movies.Result> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        val inflater = MovieRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingMovieViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
        holder.bind(listOfUpcomingMovies[position])
        holder.itemView.setOnClickListener{
            onMovieClickItem.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listOfUpcomingMovies.size
    }

    class UpcomingMovieViewHolder(private val binding: MovieRecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind (movie: com.google.movies.models.upcoming_movies.Result){
            binding.apply {
                Glide.with(rvItemImageView.context)
                    .load(IMAGE_URL + movie.posterPath)
                    .into(rvItemImageView)
                rvTitleTv.text = movie.title
                rvItemReleasedDateContentTv.text = movie.releaseDate
            }
        }
    }

    fun setData(movies: List<com.brainstem.movies.models.upcoming_movies.Result>){
        listOfUpcomingMovies = movies
        notifyDataSetChanged()
    }

    fun getListOfUpcomingMovieAdapter(): List<com.brainstem.movies.models.upcoming_movies.Result> = listOfUpcomingMovies
}