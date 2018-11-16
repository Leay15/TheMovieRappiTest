package com.rappi.themovietestrappi.topRated.component

import com.rappi.themovietestrappi.core.component.ApplicationComponent
import com.rappi.themovietestrappi.core.scopes.FragmentScope
import com.rappi.themovietestrappi.topRated.module.TopRatedModule
import com.rappi.themovietestrappi.topRated.view.fragments.TopRatedFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [TopRatedModule::class])
interface TopRatedComponent {

    fun inject(topRatedFragment: TopRatedFragment)
}