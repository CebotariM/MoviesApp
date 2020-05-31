package com.mcebotari.moviesapp.base

import com.mcebotari.moviesapp.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this).build()
}