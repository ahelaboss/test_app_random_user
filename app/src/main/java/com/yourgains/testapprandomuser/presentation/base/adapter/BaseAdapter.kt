package com.yourgains.testapprandomuser.presentation.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.yourgains.testapprandomuser.presentation.base.adapter.viewholder.BaseViewHolder

abstract class BaseAdapter<In, Vh : BaseViewHolder<In>>(
    var list: MutableList<In> = arrayListOf()
) : RecyclerView.Adapter<Vh>() {

    private var forceUpdate: Boolean = false

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(holder: Vh, position: Int, payloads: MutableList<Any>) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = list.size

    fun getItems(): List<In> = list

    @SuppressLint("NotifyDataSetChanged")
    open fun setItems(list: List<In>?) {
        list?.let {
            this.list = it.toMutableList()
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list: List<In>?) {
        list?.let {
            this.list.clear()
            this.list.addAll(it)
        }
        notifyDataSetChanged()
    }

    open fun getItem(position: Int): In? = list.getOrNull(position)

    open fun updateItem(item: In, position: Int) {
        list[position] = item
        notifyItemChanged(position)
    }

    open fun updateItem(item: In) = findIndex(item)?.let { updateItem(item, it) }

    open fun removeItem(item: In) {
        findIndex(item)?.let { position ->
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun changeItem(item: In, payload: Any) {
        findIndex(item)?.let { notifyItemChanged(it, payload) }
    }

    open fun addItem(position: Int, item: In) {
        list.add(position, item)
        notifyItemInserted(position)
    }

    open fun addItems(position: Int, items: List<In>) {
        list.addAll(position, items)
        notifyItemRangeInserted(position, list.size)
    }

    open fun addItems(items: MutableList<In>) {
        val index = list.lastIndex
        list.addAll(items)
        notifyItemRangeChanged(index, list.size)
    }

    private fun findIndex(item: In): Int? =
        list.indexOfFirst { it == item }.takeIf { it != RecyclerView.NO_POSITION }
}