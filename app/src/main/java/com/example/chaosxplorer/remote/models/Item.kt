package com.example.chaosxplorer.remote.models

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    val name: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates)