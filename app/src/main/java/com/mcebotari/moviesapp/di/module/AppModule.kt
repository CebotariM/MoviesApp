package com.mcebotari.moviesapp.di.module

import android.app.Application
import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//private const val API_KEY_TOKEN = "d6315182ada912995c9bd55d87cf5f93"
private const val API_KEY_TOKEN = "dad8a59d86a2793dda93aa485f7339c1"
private const val API_KEY_NAME = "api_key"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun context(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(true)
            .addInterceptor(createApiKeyInterceptor())
            .addInterceptor(ChuckInterceptor(context))
            .build()
    }

    private fun createApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val newUrl = request.url().newBuilder().addQueryParameter(API_KEY_NAME, API_KEY_TOKEN).build()
            request = request.newBuilder().url(newUrl).build()
            chain.proceed(request)
        }
    }
}