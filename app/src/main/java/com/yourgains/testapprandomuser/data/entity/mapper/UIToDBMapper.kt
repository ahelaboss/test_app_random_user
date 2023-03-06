package com.yourgains.testapprandomuser.data.entity.mapper

import com.yourgains.testapprandomuser.data.entity.db.UserDB
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI


object UIToDBMapper {

    fun map(response: UserUI): UserDB = response.run {
        UserDB(
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