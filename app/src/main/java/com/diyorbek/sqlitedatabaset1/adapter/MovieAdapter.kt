package com.diyorbek.sqlitedatabaset1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.sqlitedatabaset1.database.MovieDatabase
import com.diyorbek.sqlitedatabaset1.databinding.ItemLayoutBinding
import com.diyorbek.sqlitedatabaset1.model.Movie


class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallBack()) {
    lateinit var onClick: (Movie) -> Unit
    private lateinit var movieDatabase: MovieDatabase


    private class DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    inner class MovieViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textName.text = movie.name
            binding.textAutor.text = movie.autor
            binding.textDate.text = movie.data

            binding.btnDelete.setOnClickListener {
                val database = MovieDatabase(this@MovieAdapter)
                database.deleteMovie(movie.id!!)
            }

            itemView.setOnClickListener {
                onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

}