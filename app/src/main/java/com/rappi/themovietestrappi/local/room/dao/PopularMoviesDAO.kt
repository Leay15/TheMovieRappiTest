package com.rappi.themovietestrappi.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rappi.themovietestrappi.local.model.entity.PopularResponseEntity

@Dao
interface PopularMoviesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(popularResponseEntity: PopularResponseEntity)

    @androidx.room.Query("Select * from popular_response where page == :page")
    fun getPopularMovies(page: Int): PopularResponseEntity

}