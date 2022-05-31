package com.example.testrainbowsmile.api

import com.example.testrainbowsmile.model.OrderResponse
import retrofit2.http.GET

interface OrderApi {

    @GET("api/getdocumentlist")
    suspend fun getOrders(): OrderResponse
}