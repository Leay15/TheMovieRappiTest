package com.rappi.themovietestrappi.topRated.module

import com.rappi.themovietestrappi.local.room.dao.TopRatedMovieDAO
import com.rappi.themovietestrappi.local.room.dbo.RoomDBO
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
    fun providesTopRatedMovieDAO(roomDBO: RoomDBO): TopRatedMovieDAO = roomDBO.getTopRatedMoviesDAO()

    @Provides
    fun providesTopRatedPresenter(topRatedPresenterImpl: TopRatedPresenterImpl): TopRatedPresenter =
        topRatedPresenterImpl

    @Provides
    fun providesTopRatedInteractor(topRatedInteractorImpl: TopRatedInteractorImpl): TopRatedInteractor =
        topRatedInteractorImpl
}