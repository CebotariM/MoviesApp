package com.mcebotari.moviesapp.data.model

data class MovieListResponse(
    val page: Int,
    val results: List<MovieDetail>,
    val total_results: Int,
    val total_pages: Int
)