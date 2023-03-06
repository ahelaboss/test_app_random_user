package com.yourgains.testapprandomuser.domain.usecase

import com.yourgains.testapprandomuser.data.PendingResult
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.domain.repository.IUserRepository
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val userRepository: IUserRepository
) : BaseUseCase<List<UserUI>>() {

    override suspend fun execute(): PendingResult<List<UserUI>> = userRepository.getHistory()
}