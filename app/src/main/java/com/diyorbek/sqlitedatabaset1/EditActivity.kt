package com.diyorbek.sqlitedatabaset1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diyorbek.sqlitedatabaset1.database.MovieDatabase
import com.diyorbek.sqlitedatabaset1.databinding.ActivityEditBinding
import com.diyorbek.sqlitedatabaset1.model.Movie
import com.google.android.material.snackbar.Snackbar

class EditActivity : AppCompatActivity() {
    private lateinit var movieDatabase: MovieDatabase
    private val binding by lazy { ActivityEditBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        movieDatabase = MovieDatabase(this)

        val film = intent.getSerializableExtra("film") as Movie
        binding.apply {
            nameEdit.setText(film.name)
            autorEdit.setText(film.autor)
            descEdit.setText(film.desc)
            dateEdit.setText(film.data)
        }
        binding.updateBtn.setOnClickListener {
            val name1 = binding.nameEdit.text.toString().trim()
            val autor2 = binding.autorEdit.text.toString().trim()
            val desc3 = binding.descEdit.text.toString().trim()
            val date4 = binding.dateEdit.text.toString().trim()

            movieDatabase.updateMovie(Movie(film.id,name1,autor2,desc3,date4))
            Snackbar.make(it, "Updated", Snackbar.LENGTH_SHORT).show()
        }
    }
}