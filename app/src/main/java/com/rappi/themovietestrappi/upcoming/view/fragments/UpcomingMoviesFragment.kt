package com.rappi.themovietestrappi.upcoming.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.main.viewModel.CategoriesInterface
import com.rappi.themovietestrappi.net.model.response.GenresItem
import com.rappi.themovietestrappi.net.model.response.MovieResponse
import com.rappi.themovietestrappi.net.model.response.UpcomingResponse
import com.rappi.themovietestrappi.upcoming.component.DaggerUpcomingComponent
import com.rappi.themovietestrappi.upcoming.module.UpcomingModule
import com.rappi.themovietestrappi.upcoming.presentation.presenter.UpcomingPresenter
import com.rappi.themovietestrappi.upcoming.view.adapters.UpcomingMoviesAdapter
import com.rappi.themovietestrappi.upcoming.viewModel.UpcomingViewModel
import kotlinx.android.synthetic.main.fragment_upcoming_movies.*
import javax.inject.Inject

class UpcomingMoviesFragment : Fragment(), UpcomingViewModel, CategoriesInterface {

    private var currentServicePage = 1

    private val recyclerViewAdapter by lazy { UpcomingMoviesAdapter(context!!) }

    private var isFiltering = false


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
        isFiltering = false

        upcomingComponent.inject(this)
        upcomingPresenter.bind(this)

        fragment_upcoming_moview_recycler_view.adapter = recyclerViewAdapter
        fragment_upcoming_moview_recycler_view.setHasFixedSize(true)
        fragment_upcoming_moview_recycler_view.layoutManager = GridLayoutManager(context, 2)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        upcomingPresenter.unbind()
    }

    override fun onResume() {
        super.onResume()
        if (currentServicePage == 1) {
            upcomingPresenter.getUpcomingMovies(currentServicePage)
        }
    }

    override fun showLoading() {
        fragment_upcoming_moview_recycler_view.visibility = View.GONE
        fragment_upcoming_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        fragment_upcoming_moview_recycler_view.visibility = View.VISIBLE
        fragment_upcoming_progress_bar.visibility = View.GONE
    }

    private val resultsList = mutableListOf<MovieResponse>()

    override fun onGetUpcomingMovies(upcomingResponse: UpcomingResponse) {
        currentServicePage++
        resultsList.addAll(upcomingResponse.results)
        if (genre == null) {
            addItemsToAdapter(upcomingResponse.results)
        } else {
            val filterItems = resultsList.filter {
                it.genreIds.contains(genre?.id)
            }
            addItemsToAdapter(filterItems)
        }

        if (onScrollListener == null) {
            addRecyclerViewOnEndsReachUpdate()
        }
    }

    private var onScrollListener: RecyclerView.OnScrollListener? = null

    private fun addRecyclerViewOnEndsReachUpdate() {
        onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && !isFiltering) {
                    upcomingPresenter.getUpcomingMovies(currentServicePage)
                }
            }
        }

        fragment_upcoming_moview_recycler_view.addOnScrollListener(onScrollListener!!)
    }

    private fun addItemsToAdapter(moviesList: List<MovieResponse>) {
        recyclerViewAdapter.addItems(moviesList)
    }

    override fun onError(message: String?) {
    }

    var genre: GenresItem? = null

    override fun showMoviesOfCategory(genres: GenresItem?) {
        genre = genres
        isFiltering = if (genres != null) {
            val filterItems = resultsList.filter {
                it.genreIds.contains(genre?.id)
            }
            recyclerViewAdapter.showFilterItems(filterItems)
            true
        } else {
            recyclerViewAdapter.showFilterItems(resultsList)
            false
        }
    }
}
