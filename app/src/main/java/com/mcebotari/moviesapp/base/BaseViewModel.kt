package com.mcebotari.moviesapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mcebotari.moviesapp.R

abstract class BaseViewModel() : ViewModel() {

    private val errorMutableLiveData = MutableLiveData<Int>()

    fun getErrorLiveData(): LiveData<Int> = errorMutableLiveData

    fun showHttpException() {
        errorMutableLiveData.postValue(R.string.error_wrong_call)
    }

    fun showNoInternetException() {
        errorMutableLiveData.postValue(R.string.error_no_internet)
    }

    fun showGeneralException() {
        errorMutableLiveData.postValue(R.string.error_general)
    }
}