package com.rappi.themovietestrappi.main.view.activities

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.main.viewModel.BaseActivity
import com.rappi.themovietestrappi.main.viewModel.CategoriesInterface
import com.rappi.themovietestrappi.main.viewModel.MainViewModel
import com.rappi.themovietestrappi.net.model.response.GenresItem
import com.rappi.themovietestrappi.popular.view.fragments.PopularMoviesFragment
import com.rappi.themovietestrappi.topRated.view.fragments.TopRatedMoviesFragment
import com.rappi.themovietestrappi.upcoming.view.fragments.UpcomingMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*


class BaseActivity : AppCompatActivity(), BaseActivity {

    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val popularMoviesFragment by lazy { PopularMoviesFragment() }
    private val topRatedMoviesFragment by lazy { TopRatedMoviesFragment() }
    private val upcomingMoviesFragment by lazy { UpcomingMoviesFragment() }
    private var currentFragmentPosition = 0
    private var currentFragment: Fragment? = null

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
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

        subscribeToViewModel()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.requestGenres()
    }

    private fun subscribeToViewModel() {
        mainViewModel.run {
            genresResponse.observe(this@BaseActivity, Observer { genresResponse ->
                genresResponse?.run {
                    genresList = this.genres

                    setupGenresSpinner()
                    changeActiveFragment(currentFragmentPosition)

                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.activity_main_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)

        val searchManager: SearchManager =
            this@BaseActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView

            var searchAutoComplete: SearchView.SearchAutoComplete =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text)
            searchAutoComplete.setHintTextColor(Color.WHITE)
            searchAutoComplete.setTextColor(Color.WHITE)

            searchView.queryHint = "Type for a movie name"
        }
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(this@BaseActivity.componentName))

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
                UpcomingMoviesFragment()
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

    var isSettingUp = true

    private fun setupGenresSpinner() {
        val spinnerItems = getSpinnerItems()
        main_activity_genres_spinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        main_activity_genres_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
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
            .setPositiveButton("Retry") { dialog, which -> mainViewModel.requestGenres() }
            .setNegativeButton("Close") { dialog, which -> this.finish() }
            .setCancelable(false)
            .create()
            .show()
    }
}
