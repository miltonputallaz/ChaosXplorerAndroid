package com.example.chaosxplorer.di

import com.example.chaosxplorer.remote.ItemRemoteRepository
import com.example.chaosxplorer.remote.ItemRemoteRepositoryImpl
import com.example.chaosxplorer.repository.ItemRepository
import com.example.chaosxplorer.repository.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindableSingletonModule {
    @Binds
    abstract fun bindItemRepositoryImpl(
        itemRepositoryImpl: ItemRepositoryImpl
    ): ItemRepository

    @Binds
    abstract fun bindItemRemoteRepositoryImpl(
        itemRemoteRepositoryImpl: ItemRemoteRepositoryImpl
    ): ItemRemoteRepository
}