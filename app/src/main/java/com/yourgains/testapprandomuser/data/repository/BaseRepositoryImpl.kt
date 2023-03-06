package com.yourgains.testapprandomuser.data.repository

import com.yourgains.testapprandomuser.data.*
import com.yourgains.testapprandomuser.data.entity.mapper.Mapper
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import timber.log.Timber
import java.io.IOException

abstract class BaseRepositoryImpl {

    protected val voidMapper = object : Mapper<Void, Void> {
        override suspend fun map(data: Void): Void = data
    }

    protected val unitMapper = object : Mapper<Unit, Unit> {
        override suspend fun map(data: Unit): Unit = data
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T, K : Any?> execute(action: T, mapper: Mapper<T, K>?): PendingResult<K> =
        try {
            mapper?.map(action)?.let { SuccessResult(it) } ?: SuccessResult(null as K)
        } catch (ex: Throwable) {
            ErrorResult(ERROR_EXECUTION, ex.message)
        }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T, K : Any> request(call: Call<T>, mapper: Mapper<T, K>): PendingResult<K> =
        try {
            val response = call.awaitResponse()
            Timber.e("request")
            when (response.isSuccessful) {
                true -> {
                    val body = response.body()?.let { mapper.map(it) } ?: Unit as K
                    Timber.e(body.toString())
                    SuccessResult(body)
                }
                else -> getErrorModel(response)
            }
        } catch (ex: IOException) {
            ErrorResult(INTERNET_CONNECTION_ERROR, ex.message)
        }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T, K : Any> db(mapper: Mapper<T, K>, blcok: suspend () -> T): PendingResult<K> {
        val result = blcok.invoke()
        return SuccessResult(mapper.map(result))
    }


    private fun <T, K : Any> getErrorModel(response: Response<T>?): PendingResult<K> {
        val errorCode = response?.code() ?: INTERNET_CONNECTION_ERROR
        val errorMessage = response?.message()
        return ErrorResult(errorCode, errorMessage)
    }
}