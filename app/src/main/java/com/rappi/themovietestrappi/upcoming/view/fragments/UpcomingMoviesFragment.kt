package com.rappi.themovietestrappi.upcoming.view.fragments


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
import com.rappi.themovietestrappi.net.model.response.UpcomingResponse
import com.rappi.themovietestrappi.upcoming.component.DaggerUpcomingComponent
import com.rappi.themovietestrappi.upcoming.module.UpcomingModule
import com.rappi.themovietestrappi.upcoming.presentation.presenter.UpcomingPresenter
import com.rappi.themovietestrappi.upcoming.viewModel.UpcomingViewModel
import javax.inject.Inject

class UpcomingMoviesFragment : Fragment(), UpcomingViewModel, CategoriesInterface {

    @Inject
    lateinit var upcomingPresenter: UpcomingPresenter

    private val upcomingComponent by lazy {
        DaggerUpcomingComponent.builder()
            .applicationComponent(context!!.getApplicationComponent())
            .upcomingModule(UpcomingModule())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingComponent.inject(this)
        upcomingPresenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        upcomingPresenter.unbind()
    }

    override fun onResume() {
        super.onResume()
        upcomingPresenter.getUpcomingMoview(1)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onGetUpcomingMovies(upcomingResponse: UpcomingResponse) {
        Log.e("Ok", "Ok")
    }

    override fun onError(message: String?) {
    }

    override fun showMoviesOfCategory(genres: GenresItem) {

    }
}
