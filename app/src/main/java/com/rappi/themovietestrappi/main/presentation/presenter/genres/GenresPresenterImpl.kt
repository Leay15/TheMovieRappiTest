package com.rappi.themovietestrappi.main.presentation.presenter.genres

import com.rappi.themovietestrappi.main.presentation.interactor.genres.GenresInteractor
import com.rappi.themovietestrappi.main.viewModel.BaseActivity
import com.rappi.themovietestrappi.net.model.response.GenresResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GenresPresenterImpl @Inject constructor(private val genresInteractor: GenresInteractor) :
    GenresPresenter {

    override fun getGenres(callback: (genresResponse: GenresResponse) -> Unit) {
        genresInteractor.getGenres()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback(it)
            }, {
            }).dispose()
    }
}