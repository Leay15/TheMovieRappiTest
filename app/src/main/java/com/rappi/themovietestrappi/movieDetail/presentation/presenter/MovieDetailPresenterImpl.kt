package com.rappi.themovietestrappi.movieDetail.presentation.presenter

import com.rappi.themovietestrappi.movieDetail.presentation.interactor.MovieDetailInteractor
import com.rappi.themovietestrappi.movieDetail.viewModel.MovieDetailViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailPresenterImpl @Inject constructor(private val movieDetailInteractor: MovieDetailInteractor) :
    MovieDetailPresenter {

    private var movieDetailViewModel: MovieDetailViewModel? = null

    override fun bind(movieDetailViewModel: MovieDetailViewModel) {
        this.movieDetailViewModel = movieDetailViewModel
    }

    override fun unbind() {
        this.movieDetailViewModel = null
    }

    override fun getMovieDetail(movieId: Int) {
        movieDetailInteractor.getMovieDetail(movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                movieDetailViewModel?.onGetMovieDetail(it)
            }, {
                movieDetailViewModel?.onError(it.message)
            })
    }

}