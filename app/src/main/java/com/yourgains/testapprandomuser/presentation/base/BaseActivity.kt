package com.yourgains.testapprandomuser.presentation.base

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.navigation.NavigationView
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var navController: NavController? = null

    private var _viewBinding: VB? = null
    protected val viewBinding: VB get() = _viewBinding!!

    abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        _viewBinding = inflateViewBinding()
        setContentView(viewBinding.root)

        val navContainerId = getNavContainerId()
        if (navContainerId != -1) {
            val findFragmentById = supportFragmentManager.findFragmentById(navContainerId)
            val navHostFragment = findFragmentById as NavHostFragment
            navController = navHostFragment.navController
        }
//        getBaseViewModel()?.apply {
//        }
    }

    override fun onDestroy() {
        _viewBinding = null
        super.onDestroy()
    }

    open fun getBaseViewModel(): BaseViewModel? = null

//    open fun showProgressBar() {
//        if (progressDialog == null) progressDialog = ProgressDialog.show(supportFragmentManager)
//    }

    open fun hideProgressBar() {
//        contentRootView.postDelayed({
//            if (this@BaseActivity.isFinishing.not()) {
//                progressDialog?.dismiss()
//                progressDialog = null
//            }
//        }, PROGRESS_DIALOG_DELAY)
    }

    fun navigate(directions: NavDirections) = getNavController()?.navigate(directions)

    fun navigate(@IdRes navId: Int, args: Bundle? = null) =
        getNavController()?.navigate(navId, args)

    fun navigateUp() = getNavController()?.navigateUp()

    fun popBackStack(destinationId: Int, isInclusive: Boolean) =
        getNavController()?.popBackStack(destinationId, isInclusive)

    fun getNavController(): NavController? = navController

    fun getNavHostFragment(): Fragment? =
        supportFragmentManager.findFragmentById(getNavContainerId())

    protected open fun getNavContainerId(): Int = -1

    protected open fun getNavigationView(): NavigationView? = null

//    private fun handleProgress(uiEvent: UIEvent.LoadingUIEvent) {
//        when (uiEvent.isLoading) {
//            true -> showProgressBar()
//            false -> hideProgressBar()
//        }
//    }

}