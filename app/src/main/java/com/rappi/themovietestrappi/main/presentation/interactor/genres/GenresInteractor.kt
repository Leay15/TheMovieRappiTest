package com.rappi.themovietestrappi.main.presentation.interactor.genres

import com.rappi.themovietestrappi.net.model.response.GenresResponse
import io.reactivex.Observable

interface GenresInteractor {

    fun getGenres(): Observable<GenresResponse>
}