package com.mcebotari.moviesapp.di.module

import com.mcebotari.moviesapp.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}