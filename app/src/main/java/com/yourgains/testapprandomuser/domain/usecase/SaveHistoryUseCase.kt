package com.yourgains.testapprandomuser.domain.usecase

import com.yourgains.testapprandomuser.data.PendingResult
import com.yourgains.testapprandomuser.data.SuccessResult
import com.yourgains.testapprandomuser.data.entity.mapper.UIToDBMapper
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.domain.repository.IUserRepository
import javax.inject.Inject

class SaveHistoryUseCase @Inject constructor(
    private val userRepository: IUserRepository
) : BaseUseCase<Unit>() {

    lateinit var users: List<UserUI>

    override suspend fun execute(): PendingResult<Unit> {
        userRepository.saveHistory(users.map { UIToDBMapper.map(it) })
        return SuccessResult(Unit)
    }
}