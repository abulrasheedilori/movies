package com.brainstem.movies.repository.di.modules.modules

import com.brainstem.movies.repository.remote.GetMovieApi
import com.brainstem.movies.utils.MOVIE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(MOVIE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideGetMovieApi(): GetMovieApi = provideRetrofit().create(GetMovieApi::class.java)
}