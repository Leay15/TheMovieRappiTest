package com.rappi.themovietestrappi.popular.module

import com.rappi.themovietestrappi.net.retrofit.interfaces.PopularMoviesService
import com.rappi.themovietestrappi.popular.presentation.interactor.PopularMoviesInteractor
import com.rappi.themovietestrappi.popular.presentation.interactor.PopularMoviesInteractorImpl
import com.rappi.themovietestrappi.popular.presentation.presenter.PopularMoviesPresenter
import com.rappi.themovietestrappi.popular.presentation.presenter.PopularMoviesPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PopularModule {

    @Provides
    fun providesPopularServices(retrofit: Retrofit): PopularMoviesService =
        retrofit.create(PopularMoviesService::class.java)

    @Provides
    fun providesPopularPresenter(popularMoviesPresenterImpl: PopularMoviesPresenterImpl): PopularMoviesPresenter =
        popularMoviesPresenterImpl

    @Provides
    fun providesPopularInteractor(popularMoviesInteractorImpl: PopularMoviesInteractorImpl): PopularMoviesInteractor =
        popularMoviesInteractorImpl

}