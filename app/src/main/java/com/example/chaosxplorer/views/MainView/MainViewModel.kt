package com.example.chaosxplorer.views.MainView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chaosxplorer.internal.DataState
import com.example.chaosxplorer.repository.ItemRepository
import com.example.chaosxplorer.views.AlertDialogClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemRepository: ItemRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState

    init {
        //getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            itemRepository.getItems().collect {
                when (it) {
                    is DataState.Loading -> _uiState.emit(MainUIState(loadingData = true, error = null, items = emptyList()))
                    is DataState.Success -> _uiState.emit(MainUIState(items = it.data, loadingData = false, error = null))
                    is DataState.Error -> _uiState.emit(MainUIState(error = it.exception.message, items = emptyList(), loadingData = false))
                }
            }

        }
    }

    fun showDialog(alertDialogClass: AlertDialogClass) {
        _uiState.value = _uiState.value.copy(alertDialogClass = alertDialogClass)
    }


    fun hideDialog() {
        _uiState.value = _uiState.value.copy(alertDialogClass = null)
    }



}