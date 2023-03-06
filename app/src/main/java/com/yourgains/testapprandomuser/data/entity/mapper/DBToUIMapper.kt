package com.yourgains.testapprandomuser.data.entity.mapper

import com.yourgains.testapprandomuser.data.entity.db.UserDB
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI


object DBToUIMapper {

    fun map(response: UserDB): UserUI = response.run {
        UserUI(
            uuid = uuid,
            login = login,
            titleName = titleName,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            email = email,
            phone = phone,
            cell = cell,
            thumbnail = thumbnail
        )
    }
}