package com.yourgains.testapprandomuser.presentation.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yourgains.testapprandomuser.common.Navigate
import com.yourgains.testapprandomuser.data.entity.presentation.UserUI
import com.yourgains.testapprandomuser.databinding.FragmentHistoryBinding
import com.yourgains.testapprandomuser.presentation.base.adapter.decor.SpacesItemDecoration
import com.yourgains.testapprandomuser.presentation.base.fragment.BaseFragment
import com.yourgains.testapprandomuser.presentation.base.listeners.OnItemClickListener
import com.yourgains.testapprandomuser.presentation.base.viewmodel.BaseViewModel
import com.yourgains.testapprandomuser.presentation.main.adapter.UsersAdapter
import com.yourgains.testapprandomuser.presentation.main.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(), OnItemClickListener<UserUI> {

    private val viewModel by viewModels<HistoryViewModel>()

    private val adapter: UsersAdapter = UsersAdapter(listener = this)

    override fun bindViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(inflater, container, false)

    override fun getBaseViewModel(): BaseViewModel = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            usersLiveData.observe(viewLifecycleOwner, ::setItems)
        }

        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsers()
    }

    override fun onItemClick(item: UserUI) {
        viewModel.navigateToUserInfo(item)
    }

    override fun handleNavigation(pair: Pair<Navigate.Main, UserUI?>) {
        when (pair.first) {
            Navigate.Main.UserInfo -> pair.second?.let { navigateToUserInfo(it) }
            else -> Unit
        }
    }

    private fun navigateToUserInfo(user: UserUI) {
        val navDirection = HistoryFragmentDirections.actionHistoryFragmentToUserInfoFragment(user)
        navigate(navDirection)
    }

    private fun setItems(list: MutableList<UserUI>) {
        adapter.setItems(list)
    }

    private fun initAdapter() {
        with(viewBinding.fHistoryRv) {
            val padding = resources.getDimensionPixelSize(com.yourgains.testapprandomuser.R.dimen.padding_4)
            val spacesItemDecoration = SpacesItemDecoration(0, 0, padding, padding)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(spacesItemDecoration)
            adapter = this@HistoryFragment.adapter
        }
    }

}