package com.rappi.themovietestrappi.popular.view.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.main.viewModel.CategoriesInterface
import com.rappi.themovietestrappi.net.model.response.GenresItem
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import com.rappi.themovietestrappi.popular.component.DaggerPopularComponent
import com.rappi.themovietestrappi.popular.module.PopularModule
import com.rappi.themovietestrappi.popular.presentation.presenter.PopularMoviesPresenter
import com.rappi.themovietestrappi.popular.viewModel.PopularViewModel
import javax.inject.Inject

class PopularMoviesFragment : Fragment(), PopularViewModel, CategoriesInterface {

    @Inject
    lateinit var popularMoviesPresenter: PopularMoviesPresenter

    private val popularComponent by lazy {
        DaggerPopularComponent.builder()
            .applicationComponent(context!!.getApplicationComponent())
            .popularModule(PopularModule())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularComponent.inject(this)
        popularMoviesPresenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popularMoviesPresenter.unbind()
    }

    override fun onResume() {
        super.onResume()
        popularMoviesPresenter.getPopularMovies(1)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onGetMovies(popularResponse: PopularResponse) {
        Log.e("OK", "OkPopular")
    }

    override fun onError(message: String?) {

    }

    override fun showMoviesOfCategory(genres: GenresItem) {

    }
}
