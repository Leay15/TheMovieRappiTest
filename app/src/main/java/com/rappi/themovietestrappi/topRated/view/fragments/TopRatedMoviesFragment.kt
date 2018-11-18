package com.rappi.themovietestrappi.topRated.view.fragments


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
import com.rappi.themovietestrappi.net.model.response.TopRatedResponse
import com.rappi.themovietestrappi.topRated.component.DaggerTopRatedComponent
import com.rappi.themovietestrappi.topRated.module.TopRatedModule
import com.rappi.themovietestrappi.topRated.presentation.presenter.TopRatedPresenter
import com.rappi.themovietestrappi.topRated.view.adapters.TopRatedMoviesAdapter
import com.rappi.themovietestrappi.topRated.viewModel.TopRatedViewModel
import kotlinx.android.synthetic.main.fragment_top_rated.*
import javax.inject.Inject

class TopRatedMoviesFragment : Fragment(), TopRatedViewModel, CategoriesInterface {

    private var currentServicePage = 1

    private val recyclerViewAdapter by lazy { TopRatedMoviesAdapter(context!!) }

    private var isFiltering = false


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
        isFiltering = false

        topRatedComponent.inject(this)
        topRatedPresenter.bind(this)

        fragment_top_rated_moview_recycler_view.adapter = recyclerViewAdapter
        fragment_top_rated_moview_recycler_view.setHasFixedSize(true)
        fragment_top_rated_moview_recycler_view.layoutManager = GridLayoutManager(context, 2)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        topRatedPresenter.unbind()
    }

    override fun onResume() {
        super.onResume()
        if (currentServicePage == 1) {
            topRatedPresenter.getTopRatedMovies(currentServicePage)
        } else {
            hideLoading()
        }
    }

    override fun showLoading() {
        fragment_top_rated_moview_recycler_view.visibility = View.GONE
        fragment_top_rated_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        fragment_top_rated_moview_recycler_view.visibility = View.VISIBLE
        fragment_top_rated_progress_bar.visibility = View.GONE
    }

    private val resultsList = mutableListOf<MovieResponse>()

    override fun onGetTopRatedMovies(topRatedResponse: TopRatedResponse) {
        currentServicePage++
        resultsList.addAll(topRatedResponse.results)
        if (genre == null) {
            addItemsToAdapter(topRatedResponse.results)
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
                    topRatedPresenter.getTopRatedMovies(currentServicePage)
                }
            }
        }

        fragment_top_rated_moview_recycler_view.addOnScrollListener(onScrollListener!!)
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
