package com.example.chaosxplorer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofitBase(
        // Potential dependencies of this type
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://127.0.0.1:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
