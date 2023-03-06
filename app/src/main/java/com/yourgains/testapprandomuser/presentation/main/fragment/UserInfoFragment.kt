package com.yourgains.testapprandomuser.presentation.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yourgains.testapprandomuser.common.extantion.loadWithGlide
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.databinding.FragmentUserInfoBinding
import com.yourgains.testapprandomuser.presentation.base.fragment.BaseFragment
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import com.yourgains.testapprandomuser.presentation.main.viewmodel.UserInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    private val viewModel by viewModels<UserInfoViewModel>()

    override fun bindViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentUserInfoBinding =
        FragmentUserInfoBinding.inflate(inflater, container, false)

    override fun getBaseViewModel(): BaseViewModel = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            userLiveData.observe(viewLifecycleOwner, ::updateUI)
        }

        arguments?.let {
            val user = UserInfoFragmentArgs.fromBundle(it).user
            viewModel.setData(user)
        } ?: navigateUp()
    }

    private fun updateUI(user: UserUI) {
        with(viewBinding) {
            fUserInfoIv.loadWithGlide(user.thumbnail)
            fUserInfoName.text = user.getFullName()
            fUserInfoEmailValue.text = user.email
            fUserInfoPhoneValue.text = user.phone
            fUserInfoGenderValue.text = user.gender
            fUserInfoUsernameValue.text = user.login
        }
    }

}