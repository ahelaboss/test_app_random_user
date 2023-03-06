package com.yourgains.testapprandomuser.data.entity.mapper

import androidx.annotation.NonNull

interface Mapper<T, K> {
    /**
     * Apply some calculation to the input value and return some other value.
     * @param data the input value
     * @return the output value
     * @throws Exception on error
     */
    @Throws(Exception::class)
    suspend fun map(@NonNull data: T): K
}