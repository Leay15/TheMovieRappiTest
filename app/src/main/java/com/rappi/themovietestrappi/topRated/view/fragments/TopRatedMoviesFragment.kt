package com.rappi.themovietestrappi.topRated.view.fragments


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
import com.rappi.themovietestrappi.net.model.response.TopRatedResponse
import com.rappi.themovietestrappi.topRated.component.DaggerTopRatedComponent
import com.rappi.themovietestrappi.topRated.module.TopRatedModule
import com.rappi.themovietestrappi.topRated.presentation.presenter.TopRatedPresenter
import com.rappi.themovietestrappi.topRated.viewModel.TopRatedViewModel
import javax.inject.Inject

class TopRatedMoviesFragment : Fragment(), TopRatedViewModel, CategoriesInterface {

    @Inject
    lateinit var topRatedPresenter: TopRatedPresenter

    private val topRatedComponent by lazy {
        DaggerTopRatedComponent.builder()
            .applicationComponent(context!!.getApplicationComponent())
            .topRatedModule(TopRatedModule())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedComponent.inject(this)
        topRatedPresenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        topRatedPresenter.unbind()
    }

    override fun onResume() {
        super.onResume()
        topRatedPresenter.getTopRatedMovies(1)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onGetTopRatedMovies(topRatedResponse: TopRatedResponse) {
        Log.e("Ok", "OkTopRated")
    }

    override fun onError(message: String?) {
    }

    override fun showMoviesOfCategory(genres: GenresItem) {

    }
}
