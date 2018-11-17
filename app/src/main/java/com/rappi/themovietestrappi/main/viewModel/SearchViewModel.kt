package com.rappi.themovietestrappi.main.viewModel

import com.rappi.themovietestrappi.net.model.response.PopularResponse

interface SearchViewModel {

    fun showLoading()

    fun hideLoading()

    fun onGetResults(popularResponse: PopularResponse)

    fun onError(message: String?)
}