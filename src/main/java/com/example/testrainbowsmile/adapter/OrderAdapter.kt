package com.example.testrainbowsmile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testrainbowsmile.R
import com.example.testrainbowsmile.databinding.ItemDataBinding
import com.example.testrainbowsmile.model.Order

interface Listener {
    fun copyUp(order: Order)
    fun copyDown(order: Order)
}

class OrdersAdapter(private val orders: List<Order>, private val listener: Listener) :
    ListAdapter<Order, OrdersViewHolder>(OrdersDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(layoutInflater, parent, false)
        return OrdersViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}

class OrdersViewHolder(private val binding: ItemDataBinding, private val listener: Listener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(order: Order) {
        binding.apply {
            idPos.text = root.context.getString(R.string.text_id_pos, order.idPos.toString())
            idRecord.text =
                root.context.getString(R.string.text_id_record, order.idRecord.toString())
            idHdRoute.text =
                root.context.getString(R.string.text_id_hd_route, order.idHdRoute.toString())
            nomRoute.text = root.context.getString(R.string.text_nom_route, order.nomRoute)
            nomZak.text = root.context.getString(R.string.text_nom_zak, order.nomZak)
            nomNakl.text = root.context.getString(R.string.text_nom_nakl, order.nomNakl)

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.item_menu)
                    setOnMenuItemClickListener { item ->
                        when(item.itemId) {
                            R.id.copyUp -> {
                                listener.copyUp(order)
                                true
                            }
                            R.id.copyDown -> {
                                listener.copyDown(order)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}

object OrdersDiffCallback : DiffUtil.ItemCallback<Order>() {

    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.idPos == newItem.idPos
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }
}