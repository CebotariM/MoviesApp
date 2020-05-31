package com.mcebotari.moviesapp.view.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.endava.encarsharing.android.ui.base.BaseActivity
import com.mcebotari.moviesapp.R
import com.mcebotari.moviesapp.view.details.MovieDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel by provide<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelObservables()
    }

    private fun showNoConnectionAlert() {
        AlertDialog.Builder(this)
            .setMessage(R.string.error_no_internet)
            .setTitle(R.string.app_name)
            .show()
    }

    private fun setupViewModelObservables() {
        viewModel.getNavigateToDetailsAction().observe(this, Observer { navigateToDetails(it) })
        viewModel.getNetworkAvailable().observe(this, Observer { handleInternetConnection(it) })
    }

    private fun handleInternetConnection(networkAvailable: Boolean) {
        if (networkAvailable) {
            setupViewPager()
        } else {
            showNoConnectionAlert()
        }
    }

    private fun navigateToDetails(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_fragment_container_view,
                MovieDetailsFragment.getInstance(movieId),
                MovieDetailsFragment::class.java.name
            )
            .addToBackStack(null)
            .commit()
    }

    private fun setupViewPager() {
        main_tab_layout.setupWithViewPager(main_view_pager)
        main_view_pager.adapter = MainAdapter(this, supportFragmentManager)
    }
}
