package com.yourgains.testapprandomuser.presentation.base.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.yourgains.testapprandomuser.presentation.base.listeners.OnItemClickListener

abstract class BaseViewHolder<T>(
    viewBinding: ViewBinding,
    open val listener: OnItemClickListener<T>? = null
) : RecyclerView.ViewHolder(viewBinding.root) {

    abstract fun bind(data: T)
}