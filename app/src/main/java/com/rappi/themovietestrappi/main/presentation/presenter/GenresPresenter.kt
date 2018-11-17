package com.rappi.themovietestrappi.main.presentation.presenter

import com.rappi.themovietestrappi.main.viewModel.MainActivityViewModel

interface GenresPresenter {

    fun bind(mainActivityViewModel: MainActivityViewModel)

    fun unbind()

    fun getGenres()
}