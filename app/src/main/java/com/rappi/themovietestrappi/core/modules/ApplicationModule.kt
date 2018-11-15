package com.rappi.themovietestrappi.core.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Application = this.application

}