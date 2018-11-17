package com.rappi.themovietestrappi.movieDetail.view

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.core.modules.GlideApp
import com.rappi.themovietestrappi.movieDetail.component.DaggerMovieDetailComponent
import com.rappi.themovietestrappi.movieDetail.module.MovieDetailModule
import com.rappi.themovietestrappi.movieDetail.presentation.presenter.MovieDetailPresenter
import com.rappi.themovietestrappi.movieDetail.viewModel.MovieDetailViewModel
import com.rappi.themovietestrappi.net.DataConfiguration
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        movieId = intent?.extras?.getInt("movie_id") ?: -1

        movieDetailComponent.inject(this)
        movieDetailPresenter.bind(this)
        movieDetailPresenter.getMovieDetail(movieId)
    }

    override fun showLoading() {
        activity_movie_detail_progress_bar.visibility = View.VISIBLE
        activity_movie_detail_container.visibility = View.GONE
    }

    override fun hideLoading() {
        activity_movie_detail_progress_bar.visibility = View.GONE
        activity_movie_detail_container.visibility = View.VISIBLE
    }

    override fun onGetMovieDetail(movieDetail: MovieDetailResponse) {
        activity_movie_detail_movie_title.text = movieDetail.title
        activity_movie_detail_movie_popularity.text = "${movieDetail.popularity}"
        activity_movie_detail_movie_vote_average.text = "${movieDetail.voteAverage}"
        activity_movie_detail_movie_description.text = movieDetail.overview

        val uri = Uri.parse("${DataConfiguration.BASE_IMAGES_URL}${movieDetail.backdropPath}")
        GlideApp.with(this)
            .load(uri)
            .placeholder(R.drawable.ic_image_black_24dp)
            .centerCrop()
            .into(activity_movie_detail_movie_backdrop)
    }

    override fun onError(message: String?) {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
