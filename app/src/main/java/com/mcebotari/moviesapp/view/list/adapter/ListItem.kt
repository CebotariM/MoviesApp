package com.mcebotari.moviesapp.view.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mcebotari.moviesapp.data.model.MovieDetail
import com.mcebotari.moviesapp.databinding.ViewholderListMainBinding
import com.mcebotari.moviesapp.di.module.BASE_IMAGE_URL
import com.mcebotari.moviesapp.view.list.ListViewModel

class ListItem(
    private val binding: ViewholderListMainBinding,
    private val listViewModel: ListViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(movie: MovieDetail) {
        binding.movie = movie
        binding.viewModel = listViewModel
        Glide.with(binding.root)
            .load(BASE_IMAGE_URL.plus(movie.poster_path))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.viewHolderBackground)
    }
}