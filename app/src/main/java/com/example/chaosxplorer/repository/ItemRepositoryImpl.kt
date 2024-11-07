package com.example.chaosxplorer.repository

import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.remote.ItemRemoteRepository
import com.example.chaosxplorer.remote.models.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemRemoteRepository: ItemRemoteRepository
): ItemRepository {
    override suspend fun getItems(): Flow<DataState<List<Item>>> {
        return itemRemoteRepository.getItems()
    }
}