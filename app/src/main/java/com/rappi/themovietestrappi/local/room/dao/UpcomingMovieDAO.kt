package com.rappi.themovietestrappi.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rappi.themovietestrappi.local.model.entity.UpcomingResponseEntity

@Dao
interface UpcomingMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovie(upcomingResponseEntity: UpcomingResponseEntity)

    @androidx.room.Query("Select * from upcoming_response where page == :page")
    fun getUpcomingMovie(page: Int): UpcomingResponseEntity?

}