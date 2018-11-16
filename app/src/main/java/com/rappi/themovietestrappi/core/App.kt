package com.rappi.themovietestrappi.core

import android.app.Application
import android.content.Context
import com.rappi.themovietestrappi.core.component.ApplicationComponent
import com.rappi.themovietestrappi.core.component.DaggerApplicationComponent
import com.rappi.themovietestrappi.core.modules.ApplicationModule
import com.rappi.themovietestrappi.net.module.NetModule

class App : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .netModule(NetModule("https://api.themoviedb.org/3/"))
            .applicationModule(ApplicationModule(this))
            .build()
    }
}

/**
 * @return The application component from any class that have access or extends the Context class
 */
fun Context.getApplicationComponent(): ApplicationComponent = (this.applicationContext as App).applicationComponent