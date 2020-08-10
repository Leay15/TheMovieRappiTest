package com.rappi.themovietestrappi.main.presentation.presenter.genres

import com.rappi.themovietestrappi.main.viewModel.BaseActivity
import com.rappi.themovietestrappi.net.model.response.GenresResponse

interface GenresPresenter {

    fun getGenres(callback : (genresResponse: GenresResponse) -> Unit)
}