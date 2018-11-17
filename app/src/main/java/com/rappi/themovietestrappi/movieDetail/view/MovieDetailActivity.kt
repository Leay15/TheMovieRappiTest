package com.rappi.themovietestrappi.movieDetail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.movieDetail.component.DaggerMovieDetailComponent
import com.rappi.themovietestrappi.movieDetail.module.MovieDetailModule
import com.rappi.themovietestrappi.movieDetail.presentation.presenter.MovieDetailPresenter
import com.rappi.themovietestrappi.movieDetail.viewModel.MovieDetailViewModel
import com.rappi.themovietestrappi.net.model.response.MovieDetailResponse
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity(), MovieDetailViewModel {

    @Inject
    lateinit var movieDetailPresenter: MovieDetailPresenter

    private val movieDetailComponent by lazy {
        DaggerMovieDetailComponent.builder()
            .applicationComponent(this.getApplicationComponent())
            .movieDetailModule(MovieDetailModule())
            .build()
    }

    var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)

        movieDetailComponent.inject(this)
        movieDetailPresenter.bind(this)
        movieDetailPresenter.getMovieDetail(movieId)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onGetMovieDetail(movieDetail: MovieDetailResponse) {

    }

    override fun onError(message: String?) {

    }
}
