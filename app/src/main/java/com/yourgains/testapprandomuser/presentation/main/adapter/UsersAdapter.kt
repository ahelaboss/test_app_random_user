package com.yourgains.testapprandomuser.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.databinding.LiUserBinding
import com.yourgains.testapprandomuser.presentation.base.adapter.BaseAdapter
import com.yourgains.testapprandomuser.presentation.base.listeners.OnItemClickListener
import com.yourgains.testapprandomuser.presentation.main.adapter.viewholder.UserViewHolder

class UsersAdapter(
    items: MutableList<UserUI> = mutableListOf(),
    private val listener: OnItemClickListener<UserUI>
) : BaseAdapter<UserUI, UserViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = LiUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(viewBinding, listener)
    }
}