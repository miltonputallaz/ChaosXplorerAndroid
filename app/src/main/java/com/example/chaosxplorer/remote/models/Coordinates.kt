package com.example.chaosxplorer.remote.models

import com.google.gson.annotations.SerializedName

data class Coordinates (
    @SerializedName("type")
    val type: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("long")
    val long: Double)