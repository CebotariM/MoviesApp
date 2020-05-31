package com.mcebotari.moviesapp.data.service

import com.mcebotari.moviesapp.data.model.MovieDetail
import com.mcebotari.moviesapp.data.model.MovieListResponse
import com.mcebotari.moviesapp.data.source.MoviesSource
import javax.inject.Inject

interface MoviesService {
    suspend fun fetchMovieDetail(movieId: Int): MovieDetail
    suspend fun fetchPopularMovieList(page : Int) : MovieListResponse
    suspend fun fetchTopRatedMovieList(page : Int) : MovieListResponse
}

class MoviesServiceImpl @Inject constructor(private val moviesSource: MoviesSource) : MoviesService {
    override suspend fun fetchMovieDetail(movieId: Int) = moviesSource.getMovieById(movieId)
    override suspend fun fetchPopularMovieList(page: Int) = moviesSource.getPopularMovies(page)
    override suspend fun fetchTopRatedMovieList(page: Int) = moviesSource.getTopRatedMovies(page)

}