package com.diyorbek.sqlitedatabaset1.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.diyorbek.sqlitedatabaset1.model.Movie
import com.diyorbek.sqlitedatabaset1.util.Constantas.AUTOR
import com.diyorbek.sqlitedatabaset1.util.Constantas.DATE
import com.diyorbek.sqlitedatabaset1.util.Constantas.DESC
import com.diyorbek.sqlitedatabaset1.util.Constantas.ID
import com.diyorbek.sqlitedatabaset1.util.Constantas.NAME
import com.diyorbek.sqlitedatabaset1.util.Constantas.TABLE_NAME

class MovieDatabase(context: Context) : SQLiteOpenHelper(context, TABLE_NAME, null, 1),
    DatabaseService {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL, $AUTOR TEXT NOT NULL, $DESC TEXT NOT NULL, $DATE TEXT NOT NULL)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    override fun addMovie(movie: Movie) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, movie.name)
        contentValues.put(AUTOR, movie.autor)
        contentValues.put(DESC, movie.desc)
        contentValues.put(DATE, movie.data)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun deleteMovie(id: Int) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$ID = ?", arrayOf(id.toString()))
    }

    override fun updateMovie(movie: Movie) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, movie.name)
        contentValues.put(AUTOR, movie.autor)
        contentValues.put(DESC, movie.desc)
        contentValues.put(DATE, movie.data)
        database.update(TABLE_NAME, contentValues, "$ID = ?", arrayOf(movie.id.toString()))
        database.close()
    }

    override fun getAllContactMovie(): List<Movie> {
        val database = this.readableDatabase
        val moviesList = mutableListOf<Movie>()
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val movie = Movie(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                moviesList.add(movie)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return moviesList
    }
}