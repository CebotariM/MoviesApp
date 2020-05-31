package com.mcebotari.moviesapp.data.repository

import com.mcebotari.moviesapp.data.model.MovieDetail
import com.mcebotari.moviesapp.data.model.MovieListResponse
import com.mcebotari.moviesapp.data.service.MoviesService

interface MoviesRepository {
    suspend fun getMovieById(movieId: Int): MovieDetail
    suspend fun getPopularMoviesList(page: Int): MovieListResponse
    suspend fun getTopRatedMoviesList(page: Int): MovieListResponse
}

class MoviesRepositoryImpl constructor(private val moviesService: MoviesService) :
    MoviesRepository {
    override suspend fun getMovieById(movieId: Int) = moviesService.fetchMovieDetail(movieId)
    override suspend fun getPopularMoviesList(page: Int) = moviesService.fetchPopularMovieList(page)
    override suspend fun getTopRatedMoviesList(page: Int) = moviesService.fetchTopRatedMovieList(page)

}