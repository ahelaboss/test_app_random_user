package com.yourgains.testapprandomuser.domain.usecase

import com.yourgains.testapprandomuser.data.PendingResult
import com.yourgains.testapprandomuser.data.entity.mapper.UIToDBMapper
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.domain.repository.IUserRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class GetUsersUseCase @Inject constructor(
    private val userRepository: IUserRepository
) : BaseUseCase<List<UserUI>>() {

    var count by Delegates.notNull<Int>()

    override suspend fun execute(): PendingResult<List<UserUI>> = userRepository.getUsers(count)
}