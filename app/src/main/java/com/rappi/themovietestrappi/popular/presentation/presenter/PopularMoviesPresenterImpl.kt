package com.rappi.themovietestrappi.popular.presentation.presenter

import com.rappi.themovietestrappi.popular.presentation.interactor.PopularMoviesInteractor
import com.rappi.themovietestrappi.popular.viewModel.PopularViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularMoviesPresenterImpl @Inject constructor(val popularMoviesInteractor: PopularMoviesInteractor) :
    PopularMoviesPresenter {

    lateinit var popularViewModel: PopularViewModel

    override fun getPopularMovies(page: Int) {
        popularMoviesInteractor.getPopularMovies(page).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                popularViewModel.onGetMovies(it)
            }, {
                popularViewModel.onError(it.message)
            })

    }

}