package com.rappi.themovietestrappi.movieDetail.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.MovieDetailResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.MovieDetailService
import io.reactivex.Observable
import javax.inject.Inject

class MovieDetailInteractorImpl @Inject constructor(private val movieDetailService: MovieDetailService) :
    MovieDetailInteractor {

    override fun getMovieDetail(movieId: Int): Observable<MovieDetailResponse> {
        return movieDetailService.getMovieDetail(movieId, DataConfiguration.API_KEY)
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