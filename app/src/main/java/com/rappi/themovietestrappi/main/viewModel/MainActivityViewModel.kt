package com.rappi.themovietestrappi.main.viewModel

import com.rappi.themovietestrappi.net.model.response.GenresResponse
import com.rappi.themovietestrappi.net.model.response.PopularResponse

interface MainActivityViewModel {

    fun showLoading()

    fun hideLoading()

    fun onGetGenres(genresResponse: GenresResponse)

    fun onGetSearch(popularResponse: PopularResponse)

    fun onError(message: String?)

}