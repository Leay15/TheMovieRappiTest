package com.rappi.themovietestrappi.core.component

import android.app.Application
import com.rappi.themovietestrappi.core.modules.ApplicationModule
import com.rappi.themovietestrappi.local.module.RoomModule
import com.rappi.themovietestrappi.net.module.NetModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, RoomModule::class])
interface ApplicationComponent {

    fun getApplicationContext(): Application

    fun getRetrofit(): Retrofit
}