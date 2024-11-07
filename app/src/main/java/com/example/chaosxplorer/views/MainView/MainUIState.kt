package com.example.chaosxplorer.views.MainView

import com.example.chaosxplorer.remote.models.Item

data class MainUIState (val items: List<Item> = emptyList(), val error: String? = null, val loadingData: Boolean = false)