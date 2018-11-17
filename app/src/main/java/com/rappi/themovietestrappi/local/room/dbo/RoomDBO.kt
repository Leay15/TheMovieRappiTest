package com.rappi.themovietestrappi.local.room.dbo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rappi.themovietestrappi.local.model.entity.*
import com.rappi.themovietestrappi.local.room.dao.*
import com.rappi.themovietestrappi.local.typeConvertes.CustomTypeDataConverter

@Database(
    entities = arrayOf(
        GenresResponseEntity::class,
        MovieDetailResponseEntity::class,
        PopularResponseEntity::class,
        TopRatedResponseEntity::class,
        UpcomingResponseEntity::class
    ), version = 1
)
@TypeConverters(CustomTypeDataConverter::class)
public abstract class RoomDBO : RoomDatabase() {

    abstract fun getMovieGenresDAO(): GetMoviesGenresDAO
    abstract fun getMovieDetailDAO(): MovieDetailDAO
    abstract fun getPopularMoviesDAO(): PopularMoviesDAO
    abstract fun getTopRatedMoviesDAO(): TopRatedMovieDAO
    abstract fun getUpcomingMovieDAO(): UpcomingMovieDAO

}