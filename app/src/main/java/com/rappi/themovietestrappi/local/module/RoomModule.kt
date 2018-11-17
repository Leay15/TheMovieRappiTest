package com.rappi.themovietestrappi.local.module

import android.app.Application
import androidx.room.Room
import com.rappi.themovietestrappi.local.room.dao.*
import com.rappi.themovietestrappi.local.room.dbo.RoomDBO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    lateinit var roomDBO: RoomDBO

    fun RoomModule(application: Application) {
        roomDBO = Room.databaseBuilder(application, RoomDBO::class.java, "room_database").build()
    }

    @Provides
    @Singleton
    fun providesRoomDBO(): RoomDBO = this.roomDBO

    @Provides
    @Singleton
    fun providesGetMoviesGenresDAO(roomDBO: RoomDBO): GetMoviesGenresDAO = roomDBO.getMovieGenresDAO()

    @Provides
    @Singleton
    fun providesMovieDetailDAO(roomDBO: RoomDBO): MovieDetailDAO = roomDBO.getMovieDetailDAO()

    @Provides
    @Singleton
    fun providesPopularMovieDAO(roomDBO: RoomDBO): PopularMoviesDAO = roomDBO.getPopularMoviesDAO()

    @Provides
    @Singleton
    fun providesTopRatedMovieDAO(roomDBO: RoomDBO): TopRatedMovieDAO = roomDBO.getTopRatedMoviesDAO()

    @Provides
    @Singleton
    fun providesUpcomingMovieDAO(roomDBO: RoomDBO): UpcomingMovieDAO = roomDBO.getUpcomingMovieDAO()

}