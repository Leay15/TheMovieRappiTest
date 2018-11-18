package com.rappi.themovietestrappi.popular.view.fragments


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
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import com.rappi.themovietestrappi.popular.component.DaggerPopularComponent
import com.rappi.themovietestrappi.popular.module.PopularModule
import com.rappi.themovietestrappi.popular.presentation.presenter.PopularMoviesPresenter
import com.rappi.themovietestrappi.popular.view.adapters.PopularMoviesAdapter
import com.rappi.themovietestrappi.popular.viewModel.PopularViewModel
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import javax.inject.Inject

class PopularMoviesFragment : Fragment(), PopularViewModel, CategoriesInterface {

    private var currentServicePage = 1

    private val recyclerViewAdapter by lazy { PopularMoviesAdapter(context!!) }

    private var isFiltering = false

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
        isFiltering = false

        popularComponent.inject(this)
        popularMoviesPresenter.bind(this)

        fragment_popular_moview_recycler_view.adapter = recyclerViewAdapter
        fragment_popular_moview_recycler_view.setHasFixedSize(true)
        fragment_popular_moview_recycler_view.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popularMoviesPresenter.unbind()
    }

    override fun onResume() {
        super.onResume()
        if (currentServicePage == 1) {
            popularMoviesPresenter.getPopularMovies(currentServicePage)
        } else {
            hideLoading()
        }
    }

    override fun showLoading() {
        fragment_popular_moview_recycler_view.visibility = View.GONE
        fragment_popular_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        fragment_popular_moview_recycler_view.visibility = View.VISIBLE
        fragment_popular_progress_bar.visibility = View.GONE
    }

    private val resultsList = mutableListOf<MovieResponse>()

    override fun onGetMovies(popularResponse: PopularResponse) {
        currentServicePage++
        resultsList.addAll(popularResponse.results)
        if (genre == null) {
            addItemsToAdapter(popularResponse.results)
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
                    popularMoviesPresenter.getPopularMovies(currentServicePage)
                }
            }
        }

        fragment_popular_moview_recycler_view.addOnScrollListener(onScrollListener!!)
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
