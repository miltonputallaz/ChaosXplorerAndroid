package com.example.chaosxplorer.remote

import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.remote.models.Item
import kotlinx.coroutines.flow.Flow

interface ItemRemoteRepository {
    fun getItems(): Flow<DataState<List<Item>>>
}