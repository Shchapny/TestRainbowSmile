package com.example.testrainbowsmile.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testrainbowsmile.R
import com.example.testrainbowsmile.adapter.Listener
import com.example.testrainbowsmile.adapter.OrdersAdapter
import com.example.testrainbowsmile.databinding.ActivityMainBinding
import com.example.testrainbowsmile.model.Order
import com.example.testrainbowsmile.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: OrdersAdapter? = null
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.list.observe(this) { orders ->

            adapter = OrdersAdapter(orders, object : Listener {

                override fun copyUp(order: Order) {
                    viewModel.copyUp(order)
                }

                override fun copyDown(order: Order) {
                    viewModel.copyDown(order)
                }
            })

            binding.rvItems.adapter = adapter
        }

        viewModel.error.observe(this) { snackbar ->
            if (snackbar) {
                Snackbar.make(binding.root, R.string.message_error, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.sortById()
    }
}