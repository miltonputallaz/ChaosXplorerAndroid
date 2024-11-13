package com.example.chaosxplorer.views.MapView

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class MapViewModel @Inject constructor(
    locationProvider: FusedLocationProviderClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapUIState(null))
    val uiState: StateFlow<MapUIState> = _uiState

    init {
        locationProvider.lastLocation.addOnSuccessListener { location ->
            _uiState.value = _uiState.value.copy(location = location)
        }
    }
}