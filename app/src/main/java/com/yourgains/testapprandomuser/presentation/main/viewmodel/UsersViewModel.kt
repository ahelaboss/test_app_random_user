package com.yourgains.testapprandomuser.presentation.main.viewmodel

import androidx.core.text.isDigitsOnly
import com.yourgains.testapprandomuser.common.Navigate
import com.yourgains.testapprandomuser.common.SingleLiveData
import com.yourgains.testapprandomuser.data.ErrorResult
import com.yourgains.testapprandomuser.data.SuccessResult
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.domain.usecase.GetUsersUseCase
import com.yourgains.testapprandomuser.domain.usecase.SaveHistoryUseCase
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase
) : BaseViewModel() {

    companion object {
        private const val DEFAULT_COUNT = 10
    }

    val usersLiveData: SingleLiveData<MutableList<UserUI>> = SingleLiveData()

    fun getUsers(value: String?) {
        val count = value?.takeIf { it.isNotEmpty() && it.isDigitsOnly() }?.toInt() ?: DEFAULT_COUNT
        executeOnUI {
            getUsersUseCase.count = count
            when (val result = executePending { getUsersUseCase.execute() }) {
                is SuccessResult -> {
                    val users = result.body.toMutableList()
                    executePending {
                        saveHistoryUseCase.users = users
                        saveHistoryUseCase.execute()
                    }
                    usersLiveData.postValue(users)
                }
                is ErrorResult -> {
                    Timber.e(result.message)
                }
            }
        }
    }

    fun navigateToHistory() {
        navigationEvent.postValue(Navigate.Main.History to null)
    }

    fun navigateToUserInfo(item: UserUI) {
        navigationEvent.postValue(Navigate.Main.UserInfo to item)
    }
}