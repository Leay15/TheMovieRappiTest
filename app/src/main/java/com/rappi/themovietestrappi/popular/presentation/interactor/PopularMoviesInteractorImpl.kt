package com.rappi.themovietestrappi.popular.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.PopularMoviesService
import io.reactivex.Observable
import javax.inject.Inject


class PopularMoviesInteractorImpl @Inject constructor(val popularMoviesService: PopularMoviesService) :
    PopularMoviesInteractor {

    override fun getPopularMovies(page: Int): Observable<PopularResponse> {
        return popularMoviesService.getPopularMovies(DataConfiguration.API_KEY, page)
            .flatMap {
                if (it.isSuccessful) {
                    Observable.just(it.body())
                } else {
                    val gson = Gson()
                    val error = gson.fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                    Observable.error(Throwable(error.statusMessage))
                }
            }
    }

}