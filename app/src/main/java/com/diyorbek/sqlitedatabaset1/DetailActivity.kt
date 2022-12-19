package com.diyorbek.sqlitedatabaset1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diyorbek.sqlitedatabaset1.databinding.ActivityDetailBinding
import com.diyorbek.sqlitedatabaset1.model.Movie

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra("movie") as Movie

        binding.apply {
            movieName.text = movie.name
            movieAutor.text = movie.autor
            movieDesc.text = movie.desc
            movieData.text = movie.data
        }

    }
}