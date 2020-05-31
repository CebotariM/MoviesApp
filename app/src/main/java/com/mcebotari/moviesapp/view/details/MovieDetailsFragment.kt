package com.mcebotari.moviesapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.endava.encarsharing.android.ui.base.BaseFragment
import com.mcebotari.moviesapp.data.model.MovieDetail
import com.mcebotari.moviesapp.databinding.FragmentDetailsBinding
import com.mcebotari.moviesapp.di.module.BASE_IMAGE_URL

private const val MOVIE_ID_KEY = "MOVIE_ID_KEY"

class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel by provide<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()
        viewModel.fetchMovieDetails(arguments?.getInt(MOVIE_ID_KEY))
    }

    private fun setupViewModelObservers() {
        viewModel.getMovieDetailsLiveData().observe(viewLifecycleOwner, Observer { bindDetails(it) })
    }

    private fun bindDetails(movieDetail: MovieDetail) {
        binding.movie = movieDetail
        Glide.with(binding.root)
            .load(BASE_IMAGE_URL.plus(movieDetail.poster_path))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.viewHolderBackground)
    }


    companion object {

        @JvmStatic
        fun getInstance(movieId: Int) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID_KEY, movieId)
                }
            }
    }
}