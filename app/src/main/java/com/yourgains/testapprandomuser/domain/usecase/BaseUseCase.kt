package com.yourgains.testapprandomuser.domain.usecase

import com.yourgains.testapprandomuser.data.PendingResult


abstract class BaseUseCase<K> {

    abstract suspend fun execute(): PendingResult<K>
}