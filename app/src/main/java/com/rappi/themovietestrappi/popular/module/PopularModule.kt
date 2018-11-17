package com.rappi.themovietestrappi.popular.module

import com.rappi.themovietestrappi.local.room.dao.PopularMoviesDAO
import com.rappi.themovietestrappi.local.room.dbo.RoomDBO
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
    fun providesPopularMovieDAO(roomDBO: RoomDBO): PopularMoviesDAO = roomDBO.getPopularMoviesDAO()

    @Provides
    fun providesPopularPresenter(popularMoviesPresenterImpl: PopularMoviesPresenterImpl): PopularMoviesPresenter =
        popularMoviesPresenterImpl

    @Provides
    fun providesPopularInteractor(popularMoviesInteractorImpl: PopularMoviesInteractorImpl): PopularMoviesInteractor =
        popularMoviesInteractorImpl

}