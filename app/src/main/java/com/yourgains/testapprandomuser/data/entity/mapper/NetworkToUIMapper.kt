package com.yourgains.testapprandomuser.data.entity.mapper

import com.yourgains.testapprandomuser.data.entity.network.response.UserResponse
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import java.util.*


object NetworkToUIMapper {

    fun map(response: UserResponse): UserUI = response.run {
        UserUI(
            uuid = login?.uuid ?: UUID.randomUUID().toString(),
            login = login?.username,
            titleName = name?.title,
            firstName = name?.first,
            lastName = name?.last,
            gender = gender,
            email = email,
            phone = phone,
            cell = cell,
            thumbnail = picture?.thumbnail
        )
    }
}