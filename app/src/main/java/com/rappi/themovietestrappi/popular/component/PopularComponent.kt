package com.rappi.themovietestrappi.popular.component

import com.rappi.themovietestrappi.core.component.ApplicationComponent
import com.rappi.themovietestrappi.popular.module.PopularModule
import com.rappi.themovietestrappi.popular.view.fragments.PopularMoviesFragment
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [PopularModule::class])
interface PopularComponent {

    fun inject(popularMoviesFragment: PopularMoviesFragment)

}