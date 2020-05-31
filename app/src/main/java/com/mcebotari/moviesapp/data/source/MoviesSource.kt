package com.mcebotari.moviesapp.data.source

import com.mcebotari.moviesapp.data.model.MovieDetail
import com.mcebotari.moviesapp.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_ID_KEY = "movie_id"
private const val PAGE_KEY = "page"

interface MoviesSource {

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path(MOVIE_ID_KEY) movie_id: Int): MovieDetail

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query(PAGE_KEY) page : Int) : MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query(PAGE_KEY) page : Int) : MovieListResponse
}