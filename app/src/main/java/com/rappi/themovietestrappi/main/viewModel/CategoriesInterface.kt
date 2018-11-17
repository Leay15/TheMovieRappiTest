package com.rappi.themovietestrappi.main.viewModel

import com.rappi.themovietestrappi.net.model.response.GenresItem

interface CategoriesInterface {

    fun showMoviesOfCategory(genres: GenresItem)
}