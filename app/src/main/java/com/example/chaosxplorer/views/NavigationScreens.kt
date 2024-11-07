package com.example.chaosxplorer.views

import kotlinx.serialization.Serializable

sealed class NavigationScreens {
    @Serializable
    object MainView : NavigationScreens()
}