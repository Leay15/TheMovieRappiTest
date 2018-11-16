package com.rappi.themovietestrappi.upcoming.component

import com.rappi.themovietestrappi.core.component.ApplicationComponent
import com.rappi.themovietestrappi.core.scopes.FragmentScope
import com.rappi.themovietestrappi.upcoming.module.UpcomingModule
import com.rappi.themovietestrappi.upcoming.view.fragments.UpcomingMoviesFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [UpcomingModule::class])
interface UpcomingComponent {

    fun inject(upcomingMoviesFragment: UpcomingMoviesFragment)
}