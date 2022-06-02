package com.brainstem.movies.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.brainstem.movies.views.adapters.click_listeners.OnMovieClickInterface
import com.brainstem.movies.utils.IMAGE_URL
import com.brainstem.movies.databinding.MovieRecyclerviewItemBinding

class HomePageAdapter(
    private val onMovieClickItem: OnMovieClickInterface
): RecyclerView.Adapter<HomePageAdapter.HomepageViewHolder>() {
    private var listOfPopularMovies: List<com.brainstem.movies.models.popular_movies.Result> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomepageViewHolder {
        val inflater = MovieRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomepageViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: HomepageViewHolder, position: Int) {
        holder.bind(listOfPopularMovies[position])
        holder.itemView.setOnClickListener{
            onMovieClickItem.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listOfPopularMovies.size
    }

    class HomepageViewHolder(private val binding: MovieRecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind (movie: com.brainstem.movies.models.popular_movies.Result){
            binding.apply {
                Glide.with(rvItemImageView.context)
                    .load(IMAGE_URL + movie.posterPath)
                    .into(rvItemImageView)
                rvTitleTv.text = movie.title
                rvItemReleasedDateContentTv.text = movie.releaseDate
            }
        }
    }

    fun setData(movies: List<com.brainstem.movies.models.popular_movies.Result>){
        listOfPopularMovies = movies
        notifyDataSetChanged()
    }

    fun getListOfAllMembersAdapter(): List<com.brainstem.movies.models.popular_movies.Result> = listOfPopularMovies
}