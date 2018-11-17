package com.rappi.themovietestrappi.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rappi.themovietestrappi.local.model.entity.GenresResponseEntity

@Dao
interface GetMoviesGenresDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesGenres(genresResponseEntity: GenresResponseEntity)

    @androidx.room.Query("Select * from genres_response")
    fun getMoviesGenres(): GenresResponseEntity

}