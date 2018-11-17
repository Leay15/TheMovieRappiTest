package com.rappi.themovietestrappi.main.viewModel

import com.rappi.themovietestrappi.net.model.response.GenresResponse

interface MainActivityViewModel {

    fun showLoading()

    fun hideLoading()

    fun onGetGenres(genresResponse: GenresResponse)

    fun onError(message: String?)
}