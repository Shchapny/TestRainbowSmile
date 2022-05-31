package com.example.testrainbowsmile.repository

import com.example.testrainbowsmile.api.OrderApi
import com.example.testrainbowsmile.model.Order
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi,
) : OrderRepository {

    override suspend fun getFromApi(): List<Order> {
        return api.getOrders().data
    }
}