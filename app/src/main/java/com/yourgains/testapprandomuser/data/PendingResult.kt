package com.yourgains.testapprandomuser.data


const val INTERNET_CONNECTION_ERROR = -2
const val ERROR_EXECUTION = -3
const val UNPROCESSABLE_ENTITY = 422
const val FAILED_DEPENDENCY = 424
const val MULTI_LOGIN = 460
const val NOT_FOUND = 404
const val BAD_REQUEST = 400
const val PAYMENT_REQUIRED = 401
const val AUTHENTICATION_FAILED = 402

sealed class PendingResult<T : Any?>

class SuccessResult<T : Any?>(val body: T) : PendingResult<T>()

class ErrorResult<T : Any?>(
    val code: Int,
    val message: String? = null,
) : PendingResult<T>() {

    fun getErrorMessage(): String = "$code $message"

    fun isUnauthorized(): Boolean = code == AUTHENTICATION_FAILED
}
