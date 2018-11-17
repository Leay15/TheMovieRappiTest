package com.rappi.themovietestrappi.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rappi.themovietestrappi.local.model.entity.MovieDetailResponseEntity

@Dao
interface MovieDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetailResponseEntity: MovieDetailResponseEntity)

    @androidx.room.Query("Select * from movie_detail where id == :movie_id")
    fun getMovieDetail(movie_id: Int): MovieDetailResponseEntity

}