package com.diyorbek.sqlitedatabaset1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyorbek.sqlitedatabaset1.adapter.MovieAdapter
import com.diyorbek.sqlitedatabaset1.database.MovieDatabase
import com.diyorbek.sqlitedatabaset1.databinding.ActivityMainBinding
import com.diyorbek.sqlitedatabaset1.databinding.ItemLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var movieDatabase: MovieDatabase
    private val itemLaot by lazy { ItemLayoutBinding.inflate(layoutInflater) }
    private val movieAdapter by lazy { MovieAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        movieDatabase = MovieDatabase(this)





        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
        movieAdapter.submitList(movieDatabase.getAllContactMovie())

        movieAdapter.onBtnClick = {
            val bundle = bundleOf("film" to it)
            val intent = Intent(this,EditActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        movieAdapter.onClick = {
            val bundle = bundleOf("movie" to it)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        binding.addNewMovie.setOnClickListener {
            startActivity(Intent(this, EditMovieActivity::class.java))
        }
        movieAdapter.notifyDataSetChanged()
        movieAdapter.refreshDataset()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

}