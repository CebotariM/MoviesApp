package com.mcebotari.moviesapp.di.module

import com.mcebotari.moviesapp.view.details.MovieDetailsFragment
import com.mcebotari.moviesapp.view.list.PopularMoviesFragment
import com.mcebotari.moviesapp.view.list.TopRatedMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Module
abstract class FragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun popularListFragment(): PopularMoviesFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun topRatedListFragment(): TopRatedMoviesFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun movieDetailsFragment(): MovieDetailsFragment
}

@Scope
@Retention
annotation class FragmentScoped