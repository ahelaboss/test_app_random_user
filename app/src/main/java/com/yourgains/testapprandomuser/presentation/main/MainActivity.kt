package com.yourgains.testapprandomuser.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import com.yourgains.testapprandomuser.R
import com.yourgains.testapprandomuser.databinding.ActivityMainBinding
import com.yourgains.testapprandomuser.presentation.base.BaseActivity
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import com.yourgains.testapprandomuser.presentation.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getBaseViewModel(): BaseViewModel = viewModel

    override fun getNavContainerId(): Int = R.id.a_main_nav_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.e("AlexSoft")
    }

}