package com.rappi.themovietestrappi.popular.viewModel

import com.rappi.themovietestrappi.net.model.response.PopularResponse

interface PopularViewModel {

    fun showLoading()

    fun hideLoading()

    fun onGetMovies(popularResponse: PopularResponse)

    fun onError(message: String?)

}