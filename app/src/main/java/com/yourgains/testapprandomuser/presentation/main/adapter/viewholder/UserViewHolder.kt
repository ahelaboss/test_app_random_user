package com.yourgains.testapprandomuser.presentation.main.adapter.viewholder

import com.yourgains.testapprandomuser.common.extantion.loadWithGlide
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.databinding.LiUserBinding
import com.yourgains.testapprandomuser.presentation.base.adapter.viewholder.BaseViewHolder
import com.yourgains.testapprandomuser.presentation.base.listeners.OnItemClickListener


class UserViewHolder(
    private val binding: LiUserBinding,
    listener: OnItemClickListener<UserUI>
) : BaseViewHolder<UserUI>(binding, listener) {

    override fun bind(data: UserUI) {
        with(binding) {
            liUserIv.loadWithGlide(data.thumbnail)
            liUserName.text = data.getFullName()
        }
        itemView.setOnClickListener { listener?.onItemClick(data) }
    }

}