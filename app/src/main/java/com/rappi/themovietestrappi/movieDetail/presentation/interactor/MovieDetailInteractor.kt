package com.rappi.themovietestrappi.movieDetail.presentation.interactor

import com.rappi.themovietestrappi.net.model.response.MovieDetailResponse
import io.reactivex.Observable

interface MovieDetailInteractor {

    fun getMovieDetail(movieId: Int): Observable<MovieDetailResponse>
}