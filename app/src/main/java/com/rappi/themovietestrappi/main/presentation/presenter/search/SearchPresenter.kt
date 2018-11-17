package com.rappi.themovietestrappi.main.presentation.presenter.search

import com.rappi.themovietestrappi.main.viewModel.SearchViewModel

interface SearchPresenter {

    fun bind(searchViewModel: SearchViewModel)

    fun unbind()

    fun executeSearch(query: String)
}