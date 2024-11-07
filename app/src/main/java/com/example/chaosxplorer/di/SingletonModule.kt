package com.example.chaosxplorer.di

import com.example.chaosxplorer.network.ItemService
import com.example.chaosxplorer.repository.ItemRepository
import com.example.chaosxplorer.repository.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    fun provideRetrofitBase(
         retrofit: Retrofit
    ): ItemService {
        return retrofit.create(ItemService::class.java)
    }
}