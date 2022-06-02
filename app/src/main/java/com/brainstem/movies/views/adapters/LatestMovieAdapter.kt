package com.brainstem.movies.views.adapters

import android.annotation.SuppressLint
import com.brainstem.movies.models.latest_movies.LatestMovie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.brainstem.movies.views.adapters.click_listeners.OnMovieClickInterface
import com.brainstem.movies.utils.IMAGE_URL
import com.brainstem.movies.databinding.MovieRecyclerviewItemBinding

class LatestMovieAdapter(
    private val onMovieClickItem: OnMovieClickInterface
): RecyclerView.Adapter<LatestMovieAdapter.LatestMovieViewHolder>() {
    private var listOfLatestMovies = listOf<LatestMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMovieViewHolder {
        val inflater = MovieRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestMovieViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: LatestMovieViewHolder, position: Int) {
        holder.bind(listOfLatestMovies[position])
        holder.itemView.setOnClickListener{
            onMovieClickItem.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listOfLatestMovies.size
    }

    class LatestMovieViewHolder(private val binding: MovieRecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind (movie: LatestMovie){
            binding.apply {
               if(movie.posterPath == null){
                   rvTitleTv.text = "Image for ${movie.title} is not available yet"
               }
               Glide.with(rvItemImageView.context)
                   .load(IMAGE_URL + movie.posterPath)
                   .into(rvItemImageView)

                rvItemReleasedDateContentTv.text = movie.releaseDate
            }
        }
    }

    fun setData(movies: List<LatestMovie>){
        listOfLatestMovies = movies
        notifyDataSetChanged()
    }

    fun getListOfLatestMovieAdapter(): List<LatestMovie> = listOfLatestMovies
}