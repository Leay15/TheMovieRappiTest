package com.rappi.themovietestrappi.popular.presentation.presenter

import com.rappi.themovietestrappi.popular.presentation.interactor.PopularMoviesInteractor
import com.rappi.themovietestrappi.popular.viewModel.PopularViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularMoviesPresenterImpl @Inject constructor(private val popularMoviesInteractor: PopularMoviesInteractor) :
    PopularMoviesPresenter {

    private var popularViewModel: PopularViewModel? = null

    override fun getPopularMovies(page: Int) {
        popularViewModel?.showLoading()
        popularMoviesInteractor.getPopularMovies(page).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                popularViewModel?.onGetMovies(it)
                popularViewModel?.hideLoading()
            }, {
                popularViewModel?.onError(it.message)
                popularViewModel?.hideLoading()
            })

    }

    override fun bind(popularViewModel: PopularViewModel) {
        this.popularViewModel = popularViewModel
    }

    override fun unbind() {
        this.popularViewModel = null
    }
}