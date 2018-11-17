package com.rappi.themovietestrappi.main.presentation.interactor.search

import com.rappi.themovietestrappi.net.model.response.PopularResponse
import io.reactivex.Observable

interface SearchInteractor {

    fun executeSearch(query: String): Observable<PopularResponse>
}