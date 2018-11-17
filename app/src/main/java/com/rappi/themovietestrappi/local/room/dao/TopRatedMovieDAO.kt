package com.rappi.themovietestrappi.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rappi.themovietestrappi.local.model.entity.TopRatedResponseEntity

@Dao
interface TopRatedMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRated(topRatedResponseEntity: TopRatedResponseEntity)

    @androidx.room.Query("Select * from top_rated_response where page == :page")
    fun getTopRated(page: Int): TopRatedResponseEntity

}