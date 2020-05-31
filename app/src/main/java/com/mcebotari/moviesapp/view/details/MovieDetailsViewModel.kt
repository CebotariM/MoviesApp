package com.mcebotari.moviesapp.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mcebotari.moviesapp.base.BaseViewModel
import com.mcebotari.moviesapp.data.model.MovieDetail
import com.mcebotari.moviesapp.data.repository.MoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(val moviesRepository: MoviesRepository): BaseViewModel(){

    private val movieDetailsMutableLiveData = MutableLiveData<MovieDetail>()

    fun getMovieDetailsLiveData() : LiveData<MovieDetail> = movieDetailsMutableLiveData

    fun fetchMovieDetails(movieId: Int?) {
        movieId?.let {
            viewModelScope.launch {
                try {
                    movieDetailsMutableLiveData.postValue(moviesRepository.getMovieById(it))
                } catch (t : Throwable){
                    showHttpException()
                }
            }
        } ?: showGeneralException()
    }

}