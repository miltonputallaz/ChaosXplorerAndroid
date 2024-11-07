package com.example.chaosxplorer.main

import app.cash.turbine.test
import com.example.chaosxplorer.MainDispatcherRule
import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.remote.models.Coordinates
import com.example.chaosxplorer.remote.models.Item
import com.example.chaosxplorer.repository.ItemRepository
import com.example.chaosxplorer.views.MainView.MainUIState
import com.example.chaosxplorer.views.MainView.MainViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeRepository = ItemRepositoryImpl()
    private val viewModel = MainViewModel(fakeRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `obtain data from server correctly`() = runTest(StandardTestDispatcher()) {
        assertEquals(MainUIState(items = emptyList(), error = null, loadingData = false), viewModel.uiState.value)


        viewModel.getItems()


        fakeRepository.emit(DataState.Loading)
        assertEquals(MainUIState(items = emptyList(), error = null, loadingData = true), viewModel.uiState.value)

        fakeRepository.emit(DataState.Success(listOf(item)))
        assertEquals(MainUIState(items = listOf(item), error = null, loadingData = false), viewModel.uiState.value)

    }

    @Test
    fun `obtain error from the server`() = runTest(StandardTestDispatcher()) {
        assertEquals(MainUIState(items = emptyList(), error = null, loadingData = false), viewModel.uiState.value)


        viewModel.getItems()


        fakeRepository.emit(DataState.Loading)
        assertEquals(MainUIState(items = emptyList(), error = null, loadingData = true), viewModel.uiState.value)

        fakeRepository.emit(DataState.Error(throwable))
        assertEquals(MainUIState(items = emptyList(), error = throwable.message, loadingData = false), viewModel.uiState.value)
    }



    companion object {
        val item = Item("Milton", coordinates = Coordinates("123", -32.46883, -58.236765))
        val throwable = Throwable(message = "Error in backend")
    }

    internal class ItemRepositoryImpl: ItemRepository {
        private val flow = MutableSharedFlow<DataState<List<Item>>>()

        override suspend fun getItems(): Flow<DataState<List<Item>>> {
            return flow
        }

        suspend fun emit(dataState: DataState<List<Item>>) = flow.emit(dataState)
    }
}