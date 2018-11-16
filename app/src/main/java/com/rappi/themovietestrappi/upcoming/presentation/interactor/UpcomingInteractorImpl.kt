package com.rappi.themovietestrappi.upcoming.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.UpcomingResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.UpcomingMovieService
import io.reactivex.Observable
import javax.inject.Inject

class UpcomingInteractorImpl @Inject constructor(private val upcomingMovieService: UpcomingMovieService) :
    UpcomingInteractor {

    override fun getUpcomingMovies(page: Int): Observable<UpcomingResponse> {
        return upcomingMovieService.getUpcomingMovies(DataConfiguration.API_KEY, page)
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