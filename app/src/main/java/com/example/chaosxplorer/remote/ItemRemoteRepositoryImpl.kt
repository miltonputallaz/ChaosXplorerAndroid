package com.example.chaosxplorer.remote

import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.network.ItemService
import com.example.chaosxplorer.remote.models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemRemoteRepositoryImpl @Inject constructor(
    private val itemService: ItemService
): ItemRemoteRepository {
    override fun getItems(): Flow<DataState<List<Item>>> {
        return flow {
            val result = itemService.getItems()
            if (result.isSuccessful) {
                emit(DataState.Success(result.body()!!))
            } else {
                emit(DataState.Error(Throwable(message = result.message())))
            }
        }.flowOn(Dispatchers.IO)
    }
}