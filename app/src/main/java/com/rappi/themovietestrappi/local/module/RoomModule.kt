package com.rappi.themovietestrappi.local.module

import android.app.Application
import androidx.room.Room
import com.rappi.themovietestrappi.local.room.dbo.RoomDBO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesRoomDBO(application: Application): RoomDBO =
        Room.databaseBuilder(application, RoomDBO::class.java, "room_database").build()

}