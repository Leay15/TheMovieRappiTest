package com.rappi.themovietestrappi.topRated.presentation.interactor

import com.rappi.themovietestrappi.net.retrofit.interfaces.TopRatedMovieService
import javax.inject.Inject

class TopRatedInteractorImpl @Inject constructor(private val topRatedMovieService: TopRatedMovieService) :
    TopRatedInteractor {
}