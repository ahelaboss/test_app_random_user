package com.yourgains.testapprandomuser.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.JsonSyntaxException
import com.yourgains.testapprandomuser.common.Navigate
import com.yourgains.testapprandomuser.common.SingleLiveData
import com.yourgains.testapprandomuser.data.ErrorResult
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import kotlinx.coroutines.*
import retrofit2.HttpException
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    val navigationEvent = SingleLiveData<Pair<Navigate.Main, UserUI?>>()

    private val listProcess: MutableList<Any> = mutableListOf()
    private var job: Job = SupervisorJob()

    private var parentJob: Job = Job()
    private var backgroundContext: CoroutineContext = Dispatchers.IO


    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        val error = (exception as? HttpException)?.let {
            getError(it)
        } ?: ErrorResult(code = -1, message = exception.message)
        Timber.e(error.message)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + job + exceptionHandler

    protected fun showProgressBar(obj: Any) {
        listProcess.add(obj)
        takeIf { listProcess.isNotEmpty() }?.let {
            //event show progress
        }
    }

    protected fun hideProgressBar(obj: Any) {
        listProcess.remove(obj)
        takeIf { listProcess.isEmpty() }?.let {
            //event hide progress
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelParentJob()
        coroutineContext.cancel()
    }

    fun executeOnUI(block: suspend () -> Unit) {
        launch(coroutineContext) { block() }
    }

    suspend fun <T> executePending(block: suspend () -> T): T {
        try {
            showProgressBar(this)
            return withContext(Dispatchers.IO) { block() }
        } finally {
            hideProgressBar(this)
        }
    }

    private fun getError(ex: HttpException): ErrorResult<Any> {
        val responseBody = ex.response()?.errorBody()
        return takeIf { responseBody?.contentType()?.subtype == "json" }?.let {
            try {
                ErrorResult(ex.code(), ex.message())
            } catch (jsonEx: JsonSyntaxException) {
                Timber.e(jsonEx)
                ErrorResult(ex.code(), ex.message())
            }
        } ?: ErrorResult(ex.code(), ex.message())
    }

    protected fun execute(action: suspend () -> Unit?) {
        cancelParentJob()
        parentJob = Job()
        CoroutineScope(backgroundContext + parentJob).launch { action() }
    }

    protected fun cancelParentJob() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}