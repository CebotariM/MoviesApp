package com.mcebotari.moviesapp.util

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object DataBindingAdapter {
    @BindingAdapter("android:setonSwipeAction")
    @JvmStatic
    fun SwipeRefreshLayout.setOnRefreshListener(actionOnRefresh: () -> Unit) {
        setOnRefreshListener { actionOnRefresh.invoke() }
    }
}