package com.rappi.themovietestrappi.upcoming.presentation.interactor

import com.rappi.themovietestrappi.net.model.response.UpcomingResponse
import io.reactivex.Observable

interface UpcomingInteractor {

    fun getUpcomingMovies(page: Int): Observable<UpcomingResponse>
}