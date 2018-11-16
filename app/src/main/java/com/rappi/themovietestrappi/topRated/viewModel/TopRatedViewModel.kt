package com.rappi.themovietestrappi.topRated.viewModel

import com.rappi.themovietestrappi.net.model.response.TopRatedResponse

interface TopRatedViewModel {

    fun showLoading()

    fun hideLoading()

    fun onGetTopRatedMovies(topRatedResponse: TopRatedResponse)

    fun onError(message: String?)
}