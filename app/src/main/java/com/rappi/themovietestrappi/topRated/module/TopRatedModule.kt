package com.rappi.themovietestrappi.topRated.module

import com.rappi.themovietestrappi.net.retrofit.interfaces.TopRatedMovieService
import com.rappi.themovietestrappi.topRated.presentation.interactor.TopRatedInteractor
import com.rappi.themovietestrappi.topRated.presentation.interactor.TopRatedInteractorImpl
import com.rappi.themovietestrappi.topRated.presentation.presenter.TopRatedPresenter
import com.rappi.themovietestrappi.topRated.presentation.presenter.TopRatedPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class TopRatedModule {

    @Provides
    fun providesTopRatedService(retrofit: Retrofit): TopRatedMovieService =
        retrofit.create(TopRatedMovieService::class.java)

    @Provides
    fun providesTopRatedPresenter(topRatedPresenterImpl: TopRatedPresenterImpl): TopRatedPresenter =
        topRatedPresenterImpl

    @Provides
    fun providesTopRatedInteractor(topRatedInteractorImpl: TopRatedInteractorImpl): TopRatedInteractor =
        topRatedInteractorImpl
}