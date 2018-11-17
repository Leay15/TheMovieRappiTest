package com.rappi.themovietestrappi.topRated.presentation.presenter

import com.rappi.themovietestrappi.topRated.presentation.interactor.TopRatedInteractor
import com.rappi.themovietestrappi.topRated.viewModel.TopRatedViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedPresenterImpl @Inject constructor(private val topRatedInteractor: TopRatedInteractor) :
    TopRatedPresenter {

    private var topRatedViewModel: TopRatedViewModel? = null

    override fun bind(topRatedViewModel: TopRatedViewModel) {
        this.topRatedViewModel = topRatedViewModel
    }

    override fun unbind() {
        this.topRatedViewModel = null
    }

    override fun getTopRatedMovies(page: Int) {
        topRatedViewModel?.showLoading()
        topRatedInteractor.getTopRatedMoview(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                topRatedViewModel?.onGetTopRatedMovies(it)
                topRatedViewModel?.hideLoading()
            }, {
                topRatedViewModel?.onError(it?.message)
                topRatedViewModel?.hideLoading()
            })
    }
}