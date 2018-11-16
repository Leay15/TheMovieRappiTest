package com.rappi.themovietestrappi.topRated.presentation.presenter

import com.rappi.themovietestrappi.topRated.viewModel.TopRatedViewModel

interface TopRatedPresenter {

    fun bind(topRatedViewModel: TopRatedViewModel)

    fun unbind()

    fun getTopRatedMovies(page: Int)


}