package com.rappi.themovietestrappi.main.module

import com.rappi.themovietestrappi.main.presentation.interactor.GenresInteractor
import com.rappi.themovietestrappi.main.presentation.interactor.GenresInteractorImpl
import com.rappi.themovietestrappi.main.presentation.presenter.GenresPresenter
import com.rappi.themovietestrappi.main.presentation.presenter.GenresPresenterImpl
import com.rappi.themovietestrappi.net.retrofit.interfaces.GetMoviesGenresService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun providesGenresService(retrofit: Retrofit): GetMoviesGenresService =
        retrofit.create(GetMoviesGenresService::class.java)

    @Provides
    fun providesGenresPresenter(genresPresenterImpl: GenresPresenterImpl): GenresPresenter = genresPresenterImpl

    @Provides
    fun providesGenresInteractor(genresInteractorImpl: GenresInteractorImpl): GenresInteractor = genresInteractorImpl
}