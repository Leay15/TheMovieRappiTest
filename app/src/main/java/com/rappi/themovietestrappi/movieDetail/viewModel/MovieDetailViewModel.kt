package com.rappi.themovietestrappi.movieDetail.viewModel

import com.rappi.themovietestrappi.net.model.response.MovieDetailResponse

interface MovieDetailViewModel {

    fun showLoading()

    fun hideLoading()

    fun onGetMovieDetail(movieDetail: MovieDetailResponse)
}