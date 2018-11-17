package com.rappi.themovietestrappi.main.presentation.interactor.genres

import com.google.gson.Gson
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.GenresResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.GetMoviesGenresService
import io.reactivex.Observable
import javax.inject.Inject

class GenresInteractorImpl @Inject constructor(private val genresService: GetMoviesGenresService) :
    GenresInteractor {

    override fun getGenres(): Observable<GenresResponse> {
        return genresService.getMovieGenres(DataConfiguration.API_KEY).flatMap {
            if (it.isSuccessful) {
                Observable.just(it.body())
            } else {
                val error = Gson().fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                Observable.error(Throwable(error.statusMessage))
            }
        }
    }
}