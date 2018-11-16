package com.rappi.themovietestrappi.popular.presentation.presenter

import com.rappi.themovietestrappi.popular.viewModel.PopularViewModel

interface PopularMoviesPresenter {

    fun bind(popularViewModel: PopularViewModel)

    fun unbind()

    fun getPopularMovies(page: Int)

}