package com.example.chaosxplorer.views

import kotlinx.serialization.Serializable

sealed class NavigationScreens {
    @Serializable
    data object MainView : NavigationScreens()

    @Serializable
    data object MapView : NavigationScreens()
}