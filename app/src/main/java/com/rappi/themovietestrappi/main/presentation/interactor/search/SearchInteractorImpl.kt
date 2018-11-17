package com.rappi.themovietestrappi.main.presentation.interactor.search

import com.google.gson.Gson
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.SearchMovieService
import io.reactivex.Observable
import javax.inject.Inject

class SearchInteractorImpl @Inject constructor(private val searchMovieService: SearchMovieService) : SearchInteractor {

    override fun executeSearch(query: String): Observable<PopularResponse> {
        return searchMovieService.searchMovie(DataConfiguration.API_KEY, query).flatMap {
            if (it.isSuccessful) {
                Observable.just(it.body())
            } else {
                val error = Gson().fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                Observable.error(Throwable(error.statusMessage))
            }
        }
    }
}