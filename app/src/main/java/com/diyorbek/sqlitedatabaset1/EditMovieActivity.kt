package com.diyorbek.sqlitedatabaset1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diyorbek.sqlitedatabaset1.database.MovieDatabase
import com.diyorbek.sqlitedatabaset1.databinding.ActivityEditMovieBinding
import com.diyorbek.sqlitedatabaset1.model.Movie
import com.google.android.material.snackbar.Snackbar

class EditMovieActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEditMovieBinding.inflate(layoutInflater) }
    private lateinit var movieDatabase: MovieDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        movieDatabase = MovieDatabase(this)

        binding.btnSaveEdit.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val autor = binding.editAutor.text.toString().trim()
            val desc = binding.editDesc.text.toString().trim()
            val date = binding.editDate.text.toString().trim()
            if (name.isNotBlank() && autor.isNotBlank() && desc.isNotBlank() && date.isNotBlank()) {
                movieDatabase.addMovie(Movie(name = name, autor = autor, desc = desc, data = date))
                Snackbar.make(binding.root, "Saved", Snackbar.LENGTH_SHORT).show()
                binding.editName.text?.clear()
                binding.editAutor.text?.clear()
                binding.editDesc.text?.clear()
                binding.editDate.text?.clear()
            }
        }
    }
}