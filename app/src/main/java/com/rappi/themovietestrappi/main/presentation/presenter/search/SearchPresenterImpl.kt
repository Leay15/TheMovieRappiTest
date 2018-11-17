package com.rappi.themovietestrappi.main.presentation.presenter.search

import com.rappi.themovietestrappi.main.presentation.interactor.search.SearchInteractor
import com.rappi.themovietestrappi.main.viewModel.SearchViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor(private val searchInteractor: SearchInteractor) : SearchPresenter {

    private var mainActivityViewModel: SearchViewModel? = null

    override fun bind(searchViewModel: SearchViewModel) {
        this.mainActivityViewModel = searchViewModel
    }

    override fun unbind() {
        this.mainActivityViewModel = null
    }

    override fun executeSearch(query: String) {
        mainActivityViewModel?.showLoading()
        searchInteractor.executeSearch(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                mainActivityViewModel?.onGetResults(it)
                mainActivityViewModel?.hideLoading()
            }, {
                mainActivityViewModel?.onError(it.message)
                mainActivityViewModel?.hideLoading()

            })
    }

}