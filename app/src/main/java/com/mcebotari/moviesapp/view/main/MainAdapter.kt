package com.mcebotari.moviesapp.view.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mcebotari.moviesapp.R
import com.mcebotari.moviesapp.view.list.PopularMoviesFragment
import com.mcebotari.moviesapp.view.list.TopRatedMoviesFragment

class MainAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = createListOfFragments()

    private fun createListOfFragments() = listOf(PopularMoviesFragment(), TopRatedMoviesFragment())

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(TabFragmentType.values()[position].tabTitle)
    }
}

enum class TabFragmentType(@StringRes val tabTitle: Int) {
    POPULAR(R.string.tab_title_popular),
    TOP_RATED(R.string.tab_title_top_rated)
}