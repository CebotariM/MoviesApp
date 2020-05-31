package com.mcebotari.moviesapp.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mcebotari.moviesapp.base.BaseViewModel
import com.mcebotari.moviesapp.data.model.MovieListResponse
import com.mcebotari.moviesapp.data.repository.MoviesRepository
import com.mcebotari.moviesapp.util.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    BaseViewModel() {

    private val moviesListMutableLiveData = MutableLiveData<MovieListResponse>()
    private val openDetailsAction = SingleLiveEvent<Int>()

    fun getMoviesListLiveData(): LiveData<MovieListResponse> = moviesListMutableLiveData
    fun getOpenDetailsActionLiveData() = openDetailsAction

    fun fetchPopularMoviesNextPage() {
        wrapFetching { moviesRepository.getPopularMoviesList(it) }
    }

    fun fetchTopRatedMoviesNextPage() {
        wrapFetching{ moviesRepository.getTopRatedMoviesList(it) }
    }

    fun openDetailsFor(movieId : Int){
        openDetailsAction.value = movieId
    }

    private fun wrapFetching(action : suspend (pageToFetch : Int) -> MovieListResponse){
        viewModelScope.launch {
            try {
                val pageToFetch = (moviesListMutableLiveData.value?.page ?: 0) + 1
                moviesListMutableLiveData.postValue(action.invoke(pageToFetch))
            } catch (t: Throwable) {
                //todo sort exceptions
                showHttpException()
            }
        }
    }
}