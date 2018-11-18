package com.rappi.themovietestrappi.main.module

import com.rappi.themovietestrappi.local.room.dao.GetMoviesGenresDAO
import com.rappi.themovietestrappi.local.room.dbo.RoomDBO
import com.rappi.themovietestrappi.main.presentation.interactor.genres.GenresInteractor
import com.rappi.themovietestrappi.main.presentation.interactor.genres.GenresInteractorImpl
import com.rappi.themovietestrappi.main.presentation.interactor.search.SearchInteractor
import com.rappi.themovietestrappi.main.presentation.interactor.search.SearchInteractorImpl
import com.rappi.themovietestrappi.main.presentation.presenter.genres.GenresPresenter
import com.rappi.themovietestrappi.main.presentation.presenter.genres.GenresPresenterImpl
import com.rappi.themovietestrappi.main.presentation.presenter.search.SearchPresenter
import com.rappi.themovietestrappi.main.presentation.presenter.search.SearchPresenterImpl
import com.rappi.themovietestrappi.net.retrofit.interfaces.GetMoviesGenresService
import com.rappi.themovietestrappi.net.retrofit.interfaces.SearchMovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun providesGenresService(retrofit: Retrofit): GetMoviesGenresService =
        retrofit.create(GetMoviesGenresService::class.java)


    @Provides
    fun providesGetMoviesGenresDAO(roomDBO: RoomDBO): GetMoviesGenresDAO = roomDBO.getMovieGenresDAO()


    @Provides
    fun providesSearchService(retrofit: Retrofit): SearchMovieService = retrofit.create(SearchMovieService::class.java)

    @Provides
    fun providesGenresPresenter(genresPresenterImpl: GenresPresenterImpl): GenresPresenter = genresPresenterImpl

    @Provides
    fun providesGenresInteractor(genresInteractorImpl: GenresInteractorImpl): GenresInteractor = genresInteractorImpl

    @Provides
    fun providesSearchPresenter(searchPresenterImpl: SearchPresenterImpl): SearchPresenter = searchPresenterImpl

    @Provides
    fun providesSearchInteractor(searchInteractorImpl: SearchInteractorImpl): SearchInteractor = searchInteractorImpl
}