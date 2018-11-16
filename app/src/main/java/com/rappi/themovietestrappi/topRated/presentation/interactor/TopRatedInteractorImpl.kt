package com.rappi.themovietestrappi.topRated.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.TopRatedResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.TopRatedMovieService
import io.reactivex.Observable
import javax.inject.Inject

class TopRatedInteractorImpl @Inject constructor(private val topRatedMovieService: TopRatedMovieService) :
    TopRatedInteractor {

    override fun getTopRatedMoview(page: Int): Observable<TopRatedResponse> {
        return topRatedMovieService.getTopRatedMovies(DataConfiguration.API_KEY, page)
            .flatMap {
                if (it.isSuccessful) {
                    Observable.just(it.body())
                } else {
                    val error = Gson().fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                    Observable.error(Throwable(error.statusMessage))
                }
            }
    }
}