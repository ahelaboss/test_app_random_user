package com.yourgains.testapprandomuser.data.network

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class HttpLogger : HttpLoggingInterceptor.Logger {

    companion object {
        const val HTTP_TAG = "HTTP_LOGS"
    }

    override fun log(message: String) {
        Timber.tag(HTTP_TAG).i(message)
    }
}