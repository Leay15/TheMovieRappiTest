package com.rappi.themovietestrappi.main.view.activities

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.main.component.DaggerMainComponent
import com.rappi.themovietestrappi.main.module.MainModule
import com.rappi.themovietestrappi.main.presentation.presenter.genres.GenresPresenter
import com.rappi.themovietestrappi.main.viewModel.CategoriesInterface
import com.rappi.themovietestrappi.main.viewModel.MainActivityViewModel
import com.rappi.themovietestrappi.net.model.response.GenresItem
import com.rappi.themovietestrappi.net.model.response.GenresResponse
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import com.rappi.themovietestrappi.popular.view.fragments.PopularMoviesFragment
import com.rappi.themovietestrappi.topRated.view.fragments.TopRatedMoviesFragment
import com.rappi.themovietestrappi.upcoming.view.fragments.UpcomingMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainActivityViewModel {

    @Inject
    lateinit var genresPresenter: GenresPresenter

    private val mainComponent by lazy {
        DaggerMainComponent.builder()
            .applicationComponent(this.getApplicationComponent())
            .mainModule(MainModule())
            .build()
    }

    private val popularMoviesFragment by lazy { PopularMoviesFragment() }
    private val topRatedMoviesFragment by lazy { TopRatedMoviesFragment() }
    private val upcomingMoviesFragment by lazy { UpcomingMoviesFragment() }
    private var currentFragmentPosition = 0
    private var currentFragment: Fragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeActiveFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                changeActiveFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                changeActiveFragment(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setSupportActionBar(toolbar)

        mainComponent.inject(this)
        genresPresenter.bind(this)
        genresPresenter.getGenres()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.activity_main_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)

        val searchManager: SearchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView

            var searchAutoComplete: SearchView.SearchAutoComplete =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text)
            searchAutoComplete.setHintTextColor(Color.WHITE)
            searchAutoComplete.setTextColor(Color.WHITE)

            searchView.queryHint = "Type for a movie name"
        }
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))

        return super.onCreateOptionsMenu(menu)
    }

    private fun changeActiveFragment(position: Int) {
        currentFragmentPosition = position
        val fragment = when (position) {
            0 -> {
                popularMoviesFragment
            }
            1 -> {
                topRatedMoviesFragment
            }
            2 -> {
                upcomingMoviesFragment
            }
            else -> {
                Fragment()
            }
        }
        currentFragment = fragment
        main_activity_genres_spinner.setSelection(0, true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_content_frame_layout, fragment, fragment.tag)
            .commit()
    }

    lateinit var genresList: List<GenresItem>

    override fun onGetGenres(genresResponse: GenresResponse) {
        this.genresList = genresResponse.genres

        setupGenresSpinner()
        changeActiveFragment(currentFragmentPosition)

    }

    var isSettingUp = true

    private fun setupGenresSpinner() {
        val spinnerItems = getSpinnerItems()
        main_activity_genres_spinner.adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        main_activity_genres_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!isSettingUp) {
                    showMoviesOfCategory(position - 1)
                } else {
                    isSettingUp = false
                }
            }

        }

    }

    private fun getSpinnerItems(): List<String> {
        val list = mutableListOf("All Genres")

        list.addAll(
            genresList.map {
                it.name
            }
        )

        return list
    }

    private fun showMoviesOfCategory(position: Int) {
        val genreSelected = if (position >= 0) {
            genresList[position]

        } else {
            null
        }

        if (currentFragment is CategoriesInterface) {
            (currentFragment as CategoriesInterface).showMoviesOfCategory(genreSelected)
        }

    }

    override fun showLoading() {
        main_activity_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        main_activity_progress_bar.visibility = View.GONE
    }

    override fun onError(message: String?) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setTitle(getString(R.string.error_title))
            .setPositiveButton("Retry") { dialog, which -> genresPresenter.getGenres() }
            .setNegativeButton("Close") { dialog, which -> this.finish() }
            .setCancelable(false)
            .create()
            .show()
    }

    override fun onGetSearch(popularResponse: PopularResponse) {

    }
}
