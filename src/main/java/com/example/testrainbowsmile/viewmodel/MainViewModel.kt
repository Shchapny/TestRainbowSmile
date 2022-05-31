package com.example.testrainbowsmile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrainbowsmile.model.Order
import com.example.testrainbowsmile.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: OrderRepository) :
    ViewModel() {

    private var _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val listOrders = mutableListOf<Order>()

    private val _list = MutableLiveData<List<Order>>()
    val list: LiveData<List<Order>> = _list

    init {
        saveOrdersWithApi()
    }

    fun copyUp(order: Order) {
        listOrders.add(0, order)
        _list.value = listOrders
    }

    fun copyDown(order: Order) {
        listOrders.add(order)
        _list.value = listOrders
    }

    fun sortById() {
        listOrders.sortBy { it.idPos }
    }

    private fun saveOrdersWithApi() {
        viewModelScope.launch {
            try {
                val orders = repository.getFromApi()
                for (order in orders) {
                    listOrders.add(order)
                }
                _list.value = listOrders
            } catch (e: IOException) {
                _error.value = true
            }
        }
    }
}