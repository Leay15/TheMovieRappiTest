package com.rappi.themovietestrappi.movieDetail.module

import com.rappi.themovietestrappi.movieDetail.presentation.interactor.MovieDetailInteractor
import com.rappi.themovietestrappi.movieDetail.presentation.interactor.MovieDetailInteractorImpl
import com.rappi.themovietestrappi.movieDetail.presentation.presenter.MovieDetailPresenter
import com.rappi.themovietestrappi.movieDetail.presentation.presenter.MovieDetailPresenterImpl
import com.rappi.themovietestrappi.net.retrofit.interfaces.MovieDetailService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieDetailModule {

    @Provides
    fun providesMovieDetailService(retrofit: Retrofit): MovieDetailService =
        retrofit.create(MovieDetailService::class.java)

    @Provides
    fun providesMovieDetailPresenter(movieDetailPresenterImpl: MovieDetailPresenterImpl): MovieDetailPresenter =
        movieDetailPresenterImpl

    @Provides
    fun providesMovieDetailInteractor(movieDetailInteractorImpl: MovieDetailInteractorImpl): MovieDetailInteractor =
        movieDetailInteractorImpl

}