package com.example.testrainbowsmile.repository

import com.example.testrainbowsmile.model.Order

interface OrderRepository {

    suspend fun getFromApi(): List<Order>
}