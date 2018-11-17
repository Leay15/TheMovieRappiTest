package com.rappi.themovietestrappi.main.presentation.presenter.genres

import com.rappi.themovietestrappi.main.presentation.interactor.genres.GenresInteractor
import com.rappi.themovietestrappi.main.viewModel.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GenresPresenterImpl @Inject constructor(private val genresInteractor: GenresInteractor) :
    GenresPresenter {

    var mainActivityViewModel: MainActivityViewModel? = null

    override fun bind(mainActivityViewModel: MainActivityViewModel) {
        this.mainActivityViewModel = mainActivityViewModel
    }

    override fun unbind() {
        this.mainActivityViewModel = null
    }

    override fun getGenres() {
        this.mainActivityViewModel?.showLoading()
        genresInteractor.getGenres()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                this.mainActivityViewModel?.onGetGenres(it)
                this.mainActivityViewModel?.hideLoading()
            }, {
                this.mainActivityViewModel?.hideLoading()
                this.mainActivityViewModel?.onError(it.message)
            })
    }
}