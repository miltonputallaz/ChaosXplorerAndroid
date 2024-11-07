package com.example.chaosxplorer.remote

import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.network.ItemService
import com.example.chaosxplorer.remote.models.Coordinates
import com.example.chaosxplorer.remote.models.Item
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class ItemRemoteRepositoryTest {
    val itemService = mockk<ItemService>()

    val response = mockk<Response<List<Item>>>()
    val body = mockk<ResponseBody>()


    val itemRemoteRepository = ItemRemoteRepositoryImpl(itemService)

    @Test
    fun `obtain data from server correctly`() = runTest(StandardTestDispatcher()) {
        `given repository with successful response`()
        `then should receive data`()
    }

    @Test
    fun `obtain error from server`() = runTest(StandardTestDispatcher()) {
        `given repository with an error`()
        `then should receive error`()
    }

    private fun `given repository with successful response`() {
        coEvery { itemService.getItems() }.returns(Response.success(listOf(item)))
    }

    private fun `given repository with an error`() {
        every { response.isSuccessful }.returns(false)
        every { response.message() }.returns(errorString)
        every { response.errorBody() }.returns(body)
        coEvery { itemService.getItems() }.returns(response)
    }

    private suspend fun `then should receive data`() {
        val result = itemRemoteRepository.getItems().toList()[0]
        assert(result is DataState.Success<List<Item>>)
        Assert.assertEquals(result, DataState.Success(listOf(item)))
    }

    private suspend fun `then should receive error`() {
        val result = itemRemoteRepository.getItems().toList()[0]
        assert(result is DataState.Error)
        Assert.assertEquals((result as DataState.Error).exception.message, errorString)
    }

    companion object {
        val item = Item("Milton", coordinates = Coordinates("123", -32.46883, -58.236765))
        val errorString = "Error in backend"
        val throwable = Throwable(message = errorString)
    }
}