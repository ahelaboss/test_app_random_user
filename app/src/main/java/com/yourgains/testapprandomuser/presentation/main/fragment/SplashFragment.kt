package com.yourgains.testapprandomuser.presentation.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yourgains.testapprandomuser.R
import com.yourgains.testapprandomuser.databinding.FragmentSplashBinding
import com.yourgains.testapprandomuser.presentation.base.fragment.BaseFragment
import com.yourgains.testapprandomuser.presentation.main.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    companion object {
        private const val DELAY = 2000L
    }

    private val viewModel by viewModels<SplashViewModel>()

    override fun bindViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplashBinding =
        FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.fSplashLogo.postDelayed({ navigate(R.id.action_splashFragment_to_usersFragment) }, DELAY)
    }

}