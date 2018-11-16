package com.rappi.themovietestrappi.upcoming.module

import com.rappi.themovietestrappi.net.retrofit.interfaces.UpcomingMovieService
import com.rappi.themovietestrappi.upcoming.presentation.interactor.UpcomingInteractor
import com.rappi.themovietestrappi.upcoming.presentation.interactor.UpcomingInteractorImpl
import com.rappi.themovietestrappi.upcoming.presentation.presenter.UpcomingPresenter
import com.rappi.themovietestrappi.upcoming.presentation.presenter.UpcomingPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UpcomingModule {

    @Provides
    fun provideUpcomingService(retrofit: Retrofit): UpcomingMovieService =
        retrofit.create(UpcomingMovieService::class.java)

    @Provides
    fun providesUpcomingPresenter(upcomingPresenterImpl: UpcomingPresenterImpl): UpcomingPresenter =
        upcomingPresenterImpl

    @Provides
    fun providesUpcomingInteractor(upcomingInteractorImpl: UpcomingInteractorImpl): UpcomingInteractor =
        upcomingInteractorImpl
}