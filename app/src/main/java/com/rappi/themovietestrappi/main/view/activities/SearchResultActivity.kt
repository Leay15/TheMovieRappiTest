package com.rappi.themovietestrappi.main.view.activities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.main.component.DaggerMainComponent
import com.rappi.themovietestrappi.main.module.MainModule
import com.rappi.themovietestrappi.main.presentation.presenter.search.SearchPresenter
import com.rappi.themovietestrappi.main.viewModel.SearchViewModel
import com.rappi.themovietestrappi.net.model.response.MovieResponse
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import com.rappi.themovietestrappi.popular.view.adapters.PopularMoviesAdapter
import kotlinx.android.synthetic.main.activity_search_result.*
import javax.inject.Inject

class SearchResultActivity : AppCompatActivity(), SearchViewModel {

    private val recyclerViewAdapter by lazy { PopularMoviesAdapter(this) }

    @Inject
    lateinit var searchPresenter: SearchPresenter

    private val mainComponent by lazy {
        DaggerMainComponent.builder()
            .applicationComponent(this.getApplicationComponent())
            .mainModule(MainModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        search_activity_recycler_view.adapter = recyclerViewAdapter
        search_activity_recycler_view.setHasFixedSize(true)
        search_activity_recycler_view.layoutManager = GridLayoutManager(this, 2)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mainComponent.inject(this)
        searchPresenter.bind(this)
        handleIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        searchPresenter.unbind()
    }

    private fun handleIntent(intent: Intent?) {

        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            searchPresenter.executeSearch(query)
        }

    }

    override fun showLoading() {
        search_activity_progress_bar.visibility = View.VISIBLE
        search_activity_recycler_view.visibility = View.GONE
    }

    override fun hideLoading() {
        search_activity_progress_bar.visibility = View.GONE
        search_activity_recycler_view.visibility = View.VISIBLE
    }

    private val resultsList = mutableListOf<MovieResponse>()

    override fun onGetResults(popularResponse: PopularResponse) {
        resultsList.addAll(popularResponse.results)
        addItemsToAdapter(popularResponse.results)
    }


    private fun addItemsToAdapter(moviesList: List<MovieResponse>) {
        recyclerViewAdapter.addItems(moviesList)
    }

    override fun onError(message: String?) {

    }
}
