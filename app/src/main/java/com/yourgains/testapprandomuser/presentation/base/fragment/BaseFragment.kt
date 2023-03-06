package com.yourgains.testapprandomuser.presentation.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.viewbinding.ViewBinding
import com.yourgains.testapprandomuser.common.Navigate
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.presentation.base.BaseActivity
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import timber.log.Timber

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _viewBinding: VB? = null
    protected val viewBinding: VB get() = _viewBinding!!

    private var baseActivity: BaseActivity<*>? = null

    abstract fun bindViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as? BaseActivity<*>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = bindViewBinding(inflater, container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBaseViewModel()?.apply {
            navigationEvent.observe(viewLifecycleOwner) { handleNavigation(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    protected open fun getBaseViewModel(): BaseViewModel? = null

    protected open fun handleNavigation(pair: Pair<Navigate.Main, UserUI?>) {}

    protected fun navigate(id: Int, bundle: Bundle? = null) {
        val navController = baseActivity?.getNavController()
        val action = navController?.currentDestination?.getAction(id)
        if (action != null) navController.navigate(id, bundle)
    }

    protected fun navigate(directions: NavDirections) = baseActivity?.navigate(directions)

    protected fun navigateUp() = baseActivity?.navigateUp()

    protected fun popBackStack(destinationId: Int, isInclusive: Boolean) =
        baseActivity?.popBackStack(destinationId, isInclusive)

    protected fun getNavHostFragment() = baseActivity?.getNavHostFragment()

    protected fun getNavController() = baseActivity?.getNavController()

    protected fun hideKeyboard() {
        try {
            baseActivity?.let {
                val imm: InputMethodManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                it.currentFocus?.windowToken?.let { token -> imm.hideSoftInputFromWindow(token, 0) }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}