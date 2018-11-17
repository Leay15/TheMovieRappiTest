package com.rappi.themovietestrappi.movieDetail.presentation.presenter

import com.rappi.themovietestrappi.movieDetail.viewModel.MovieDetailViewModel

interface MovieDetailPresenter {

    fun bind(movieDetailViewModel: MovieDetailViewModel)

    fun unbind()

    fun getMovieDetail(movieId: Int)
}