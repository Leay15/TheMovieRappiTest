package com.rappi.themovietestrappi.popular.presentation.interactor

import com.rappi.themovietestrappi.net.model.response.PopularResponse
import io.reactivex.Observable

interface PopularMoviesInteractor {

    fun getPopularMovies(page: Int): Observable<PopularResponse>
}