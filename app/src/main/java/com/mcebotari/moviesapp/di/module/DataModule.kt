package com.mcebotari.moviesapp.di.module

import com.mcebotari.moviesapp.data.repository.MoviesRepository
import com.mcebotari.moviesapp.data.repository.MoviesRepositoryImpl
import com.mcebotari.moviesapp.data.service.MoviesService
import com.mcebotari.moviesapp.data.service.MoviesServiceImpl
import com.mcebotari.moviesapp.data.source.MoviesSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMoviesDataSource(retrofitInstance: Retrofit) =
        retrofitInstance.create(MoviesSource::class.java)

    @Provides
    @Singleton
    fun provideMoviesService(moviesSource: MoviesSource): MoviesService =
        MoviesServiceImpl(moviesSource)

    @Provides
    @Singleton
    fun provideMovieRepository(moviesService: MoviesService): MoviesRepository =
        MoviesRepositoryImpl(moviesService)

    @Provides
    @Singleton
    fun provideTest() = "test"
}