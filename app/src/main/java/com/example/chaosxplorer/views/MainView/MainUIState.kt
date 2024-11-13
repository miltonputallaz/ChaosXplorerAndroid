package com.example.chaosxplorer.views.MainView

import com.example.chaosxplorer.remote.models.Item
import com.example.chaosxplorer.views.AlertDialogClass

data class MainUIState (val items: List<Item> = emptyList(), val error: String? = null, val loadingData: Boolean = false, val alertDialogClass: AlertDialogClass? = null)