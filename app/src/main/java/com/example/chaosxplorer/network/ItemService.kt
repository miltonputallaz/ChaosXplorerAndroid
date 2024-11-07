package com.example.chaosxplorer.network

import com.example.chaosxplorer.remote.models.Item
import retrofit2.Response
import retrofit2.http.GET

interface ItemService {
    @GET("items")
    suspend fun getItems(): Response<List<Item>>
}