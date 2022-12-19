package com.diyorbek.sqlitedatabaset1.database

import com.diyorbek.sqlitedatabaset1.model.Movie

interface DatabaseService {
    fun addMovie(movie: Movie)
    fun deleteMovie(id: Int)
    fun updateMovie(movie: Movie)
    fun getAllContactMovie(): List<Movie>
}