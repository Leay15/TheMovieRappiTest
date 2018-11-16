package com.rappi.themovietestrappi.upcoming.viewModel

import com.rappi.themovietestrappi.net.model.response.UpcomingResponse

interface UpcomingViewModel {

    fun showLoading()

    fun hideLoading()

    fun onGetUpcomingMovies(upcomingResponse: UpcomingResponse)

    fun onError(message: String?)
}