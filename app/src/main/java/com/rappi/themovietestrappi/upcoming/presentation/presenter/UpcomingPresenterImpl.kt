package com.rappi.themovietestrappi.upcoming.presentation.presenter

import com.rappi.themovietestrappi.upcoming.presentation.interactor.UpcomingInteractor
import com.rappi.themovietestrappi.upcoming.viewModel.UpcomingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpcomingPresenterImpl @Inject constructor(val upcomingInteractor: UpcomingInteractor) : UpcomingPresenter {

    private var upcomingViewModel: UpcomingViewModel? = null

    override fun bind(upcomingViewModel: UpcomingViewModel) {
        this.upcomingViewModel = upcomingViewModel
    }

    override fun unbind() {
        this.upcomingViewModel = null
    }

    override fun getUpcomingMovies(page: Int) {
        this.upcomingViewModel?.showLoading()
        upcomingInteractor.getUpcomingMovies(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                upcomingViewModel?.onGetUpcomingMovies(it)
                upcomingViewModel?.hideLoading()
            }, {
                upcomingViewModel?.onError(it.message)
                upcomingViewModel?.hideLoading()
            })
    }
}