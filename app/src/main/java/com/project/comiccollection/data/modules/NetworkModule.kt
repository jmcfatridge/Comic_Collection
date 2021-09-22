package com.project.comiccollection.data.modules

import com.project.comiccollection.data.remote.ComicService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://gateway.marvel.com:443"

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun providesRetrofit(moshi: Moshi) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun providesComicService(retrofit: Retrofit) : ComicService = retrofit.create(ComicService::class.java)
}