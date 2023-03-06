package com.yourgains.testapprandomuser.presentation.main.viewmodel

import com.yourgains.testapprandomuser.common.SingleLiveData
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor() : BaseViewModel() {

    val userLiveData: SingleLiveData<UserUI> = SingleLiveData()

    fun setData(user: UserUI) {
        userLiveData.postValue(user)
    }

}