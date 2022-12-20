package com.diyorbek.sqlitedatabaset1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.sqlitedatabaset1.EditActivity
import com.diyorbek.sqlitedatabaset1.MainActivity
import com.diyorbek.sqlitedatabaset1.database.MovieDatabase
import com.diyorbek.sqlitedatabaset1.databinding.ItemLayoutBinding
import com.diyorbek.sqlitedatabaset1.model.Movie


class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallBack()) {
    lateinit var onClick: (Movie) -> Unit
    lateinit var onBtnClick: (Movie) -> Unit
    private lateinit var movieDatabase: MovieDatabase
    private lateinit var context: Context


    private class DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
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

    inner class MovieViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textName.text = movie.name
            binding.textAutor.text = movie.autor
            binding.textDate.text = movie.data

            binding.btnDelete.setOnClickListener {
                val database = MovieDatabase(context)
                database.deleteMovie(movie.id!!)
                Toast.makeText(context, "Successfully deleted! Rejoin App", Toast.LENGTH_SHORT)
                    .show()
                notifyDataSetChanged()
                refreshDataset()
            }
            binding.btnEdit.setOnClickListener {
                onBtnClick(movie)
            }

            itemView.setOnClickListener {
                onClick(movie)
            }
        }
    }

    fun refreshDataset() {
        notifyDataSetChanged()

    }
}