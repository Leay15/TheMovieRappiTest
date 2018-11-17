package com.rappi.themovietestrappi.movieDetail.component

import com.rappi.themovietestrappi.core.component.ApplicationComponent
import com.rappi.themovietestrappi.core.scopes.ActivityScope
import com.rappi.themovietestrappi.movieDetail.module.MovieDetailModule
import com.rappi.themovietestrappi.movieDetail.view.MovieDetailActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [MovieDetailModule::class])
interface MovieDetailComponent {

    fun inject(movieDetailActivity: MovieDetailActivity)
}