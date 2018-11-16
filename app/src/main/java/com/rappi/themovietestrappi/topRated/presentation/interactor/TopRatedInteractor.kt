package com.rappi.themovietestrappi.topRated.presentation.interactor

import com.rappi.themovietestrappi.net.model.response.TopRatedResponse
import io.reactivex.Observable

interface TopRatedInteractor {

    fun getTopRatedMoview(page: Int): Observable<TopRatedResponse>
}