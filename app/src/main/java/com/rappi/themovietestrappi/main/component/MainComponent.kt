package com.rappi.themovietestrappi.main.component

import com.rappi.themovietestrappi.core.component.ApplicationComponent
import com.rappi.themovietestrappi.core.scopes.ActivityScope
import com.rappi.themovietestrappi.main.module.MainModule
import com.rappi.themovietestrappi.main.view.activities.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [MainModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}