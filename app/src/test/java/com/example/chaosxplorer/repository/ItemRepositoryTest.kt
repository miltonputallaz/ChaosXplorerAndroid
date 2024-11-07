package com.example.chaosxplorer.repository

import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.network.ItemService
import com.example.chaosxplorer.remote.ItemRemoteRepository
import com.example.chaosxplorer.remote.models.Coordinates
import com.example.chaosxplorer.remote.models.Item
import com.example.chaosxplorer.views.MainView.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ItemRepositoryTest {
    val itemRemoteRepository = mockk<ItemRemoteRepository>()

    val itemRepository = ItemRepositoryImpl(itemRemoteRepository)

    @Test
    fun `obtain data from server correctly`() = runTest {
        `given repository with successful response`()

        `then should receive data`()
    }

    @Test
    fun `obtain error from the server`() = runTest {
        `given repository with wrong response from backend`()

        `then should receive error`()
    }

    private fun `given repository with successful response`() {
        coEvery { itemRemoteRepository.getItems() } returns flow {
            emit(DataState.Loading)
            emit(DataState.Success(listOf(item)))
        }
    }

    private fun `given repository with wrong response from backend`() {
        coEvery { itemRemoteRepository.getItems() } returns flow {
            emit(DataState.Loading)
            emit(DataState.Error(throwable))
        }
    }



    private suspend fun `then should receive data`() {
        val messages = itemRepository.getItems().toList()
        Assert.assertEquals(DataState.Loading, messages[0])
        Assert.assertEquals(DataState.Success(listOf(item)), messages[1])
    }

    private suspend fun `then should receive error`() {
        val messages = itemRepository.getItems().toList()
        Assert.assertEquals(DataState.Loading, messages[0])
        Assert.assertEquals(DataState.Error(throwable), messages[1])
    }

    companion object {
        val item = Item("Milton", coordinates = Coordinates("123", -32.46883, -58.236765))
        val throwable = Throwable(message = "Error in backend")
    }
}
