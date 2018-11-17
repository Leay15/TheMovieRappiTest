package com.rappi.themovietestrappi.main.presentation.presenter.search

import com.rappi.themovietestrappi.main.viewModel.MainActivityViewModel

interface SearchPresenter {

    fun bind(mainActivityViewModel: MainActivityViewModel)

    fun unbind()

    fun executeSearch(query: String)
}