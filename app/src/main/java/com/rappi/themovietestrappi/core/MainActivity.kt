package com.rappi.themovietestrappi.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.popular.view.fragments.PopularMoviesFragment
import com.rappi.themovietestrappi.topRated.view.fragments.TopRatedMoviesFragment
import com.rappi.themovietestrappi.upcoming.view.fragments.UpcomingMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val popularMoviesFragment by lazy { PopularMoviesFragment() }
    private val topRatedMoviesFragment by lazy { TopRatedMoviesFragment() }
    private val upcomingMoviesFragment by lazy { UpcomingMoviesFragment() }
    private var currentFragmentPosition = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                currentFragmentPosition = 0
                changeActiveFragment(currentFragmentPosition)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                currentFragmentPosition = 1
                changeActiveFragment(currentFragmentPosition)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                currentFragmentPosition = 2
                changeActiveFragment(currentFragmentPosition)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        changeActiveFragment(currentFragmentPosition)
    }

    private fun changeActiveFragment(position: Int) {
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

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_content_frame_layout, fragment, fragment.tag)
            .commit()
    }
}
