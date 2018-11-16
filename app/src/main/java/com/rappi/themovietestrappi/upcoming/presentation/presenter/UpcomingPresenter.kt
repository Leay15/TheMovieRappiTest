package com.rappi.themovietestrappi.upcoming.presentation.presenter

import com.rappi.themovietestrappi.upcoming.viewModel.UpcomingViewModel

interface UpcomingPresenter {

    fun bind(upcomingViewModel: UpcomingViewModel)

    fun unbind()

    fun getUpcomingMoview(page: Int)
}