package com.mcebotari.moviesapp.view.main

import androidx.lifecycle.viewModelScope
import com.mcebotari.moviesapp.base.BaseViewModel
import com.mcebotari.moviesapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.InetAddress
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel(){

    private val navigateToDetailsAction = SingleLiveEvent<Int>()
    private val networkAvailableAction = SingleLiveEvent<Boolean>()

    fun getNavigateToDetailsAction() = navigateToDetailsAction
    fun getNetworkAvailable() = networkAvailableAction

    fun navigateToDetails(movieId: Int) {
        navigateToDetailsAction.value = movieId
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                networkAvailableAction.postValue(!InetAddress.getByName("google.com").equals(""))
            } catch (e: Exception) {
                networkAvailableAction.postValue(false)
            }
        }
    }
}