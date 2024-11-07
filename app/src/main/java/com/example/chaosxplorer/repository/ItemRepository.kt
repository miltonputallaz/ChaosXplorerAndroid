package com.example.chaosxplorer.repository

import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.remote.models.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItems(): Flow<DataState<List<Item>>>
}