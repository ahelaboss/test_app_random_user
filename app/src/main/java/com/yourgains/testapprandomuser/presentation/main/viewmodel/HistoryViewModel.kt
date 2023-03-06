package com.yourgains.testapprandomuser.presentation.main.viewmodel

import com.yourgains.testapprandomuser.common.Navigate
import com.yourgains.testapprandomuser.common.SingleLiveData
import com.yourgains.testapprandomuser.data.ErrorResult
import com.yourgains.testapprandomuser.data.SuccessResult
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.domain.usecase.GetHistoryUseCase
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
): BaseViewModel() {

    val usersLiveData: SingleLiveData<MutableList<UserUI>> = SingleLiveData()

    fun getUsers() {
        executeOnUI {
            when (val result = executePending { getHistoryUseCase.execute() }) {
                is SuccessResult -> usersLiveData.postValue(result.body.toMutableList())
                is ErrorResult -> {
                    Timber.e(result.message)
                }
            }
        }
    }

    fun navigateToUserInfo(item: UserUI) {
        navigationEvent.postValue(Navigate.Main.UserInfo to item)
    }
}