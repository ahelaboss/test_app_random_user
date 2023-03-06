package com.yourgains.testapprandomuser.presentation.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yourgains.testapprandomuser.R
import com.yourgains.testapprandomuser.common.Navigate
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.databinding.FragmentUsersBinding
import com.yourgains.testapprandomuser.presentation.base.adapter.decor.SpacesItemDecoration
import com.yourgains.testapprandomuser.presentation.base.fragment.BaseFragment
import com.yourgains.testapprandomuser.presentation.base.listeners.OnItemClickListener
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import com.yourgains.testapprandomuser.presentation.main.adapter.UsersAdapter
import com.yourgains.testapprandomuser.presentation.main.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(), OnItemClickListener<UserUI> {

    private val viewModel by viewModels<UsersViewModel>()

    private val adapter: UsersAdapter = UsersAdapter(listener = this)

    override fun bindViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentUsersBinding =
        FragmentUsersBinding.inflate(inflater, container, false)

    override fun getBaseViewModel(): BaseViewModel = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("AlexSoft")

        with(viewModel) {
            usersLiveData.observe(viewLifecycleOwner, ::setItems)
        }

        initAdapter()
        initListeners()
    }

    override fun onItemClick(item: UserUI) {
        viewModel.navigateToUserInfo(item)
    }

    override fun handleNavigation(pair: Pair<Navigate.Main, UserUI?>) {
        when (pair.first) {
            Navigate.Main.History -> navigate(R.id.action_usersFragment_to_historyFragment)
            Navigate.Main.UserInfo -> pair.second?.let { navigateToUserInfo(it) }
            else -> Unit
        }
    }

    private fun navigateToUserInfo(user: UserUI) {
        val navDirection = UsersFragmentDirections.actionUsersFragmentToUserInfoFragment(user)
        navigate(navDirection)
    }

    private fun setItems(list: MutableList<UserUI>) {
        adapter.setItems(list)
    }

    private fun initListeners() {
        with(viewBinding) {
            fUsersGetBtn.setOnClickListener {
                hideKeyboard()
                viewModel.getUsers(fUsersCountEt.text.toString())
            }
            fUsersHistoryBtn.setOnClickListener {
                hideKeyboard()
                viewModel.navigateToHistory()
            }
        }
    }

    private fun initAdapter() {
        with(viewBinding.fUsersRv) {
            val padding = resources.getDimensionPixelSize(R.dimen.padding_4)
            val spacesItemDecoration = SpacesItemDecoration(0, 0, padding, padding)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(spacesItemDecoration)
            adapter = this@UsersFragment.adapter
        }
    }

}