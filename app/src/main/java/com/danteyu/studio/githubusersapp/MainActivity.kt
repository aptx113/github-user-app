/*
 * Copyright 2021 Dante Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danteyu.studio.githubusersapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.danteyu.studio.githubusersapp.databinding.ActivityMainBinding
import com.danteyu.studio.githubusersapp.ext.showToast
import com.danteyu.studio.githubusersapp.utils.NetworkListener
import com.danteyu.studio.githubusersapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var networkListener: NetworkListener
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        networkListener = NetworkListener()
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.mainSwipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.refreshStatusFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { binding.mainSwipeRefresh.isRefreshing = it }
            .launchIn(lifecycleScope)

        setupRecyclerView()
        requestApiData()
        listenNetworkAvailability()
    }

    private fun setupRecyclerView() {
        binding.mainRecycler.adapter = adapter
    }

    private fun listenNetworkAvailability() {
        networkListener.checkNetworkAvailability(this)
            .onEach { hasNetwork ->
                Timber.d(hasNetwork.toString())
                showNetworkStatus(hasNetwork)
            }.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .launchIn(lifecycleScope)
    }

    private fun requestApiData() {
        viewModel.getGitHubUsers()
        lifecycleScope.launch {

            viewModel.gitHubUsersFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { response ->
                    when (response) {
                        is Resource.Success -> response.data?.let { adapter.submitList(it) }
                        is Resource.Error -> showToast(response.message.toString())
                        is Resource.GenericError -> Timber.e("code = ${response.code}, error = ${response.error}")
                        is Resource.Loading -> {
                            binding.mainProgress.visibility = View.VISIBLE
                        }
                    }
                }
        }
    }

    private fun showNetworkStatus(hasNetwork: Boolean) {
        if (!hasNetwork) {
            showToast(getString(R.string.no_internet_connection))
        } else {
            showToast(getString(R.string.back_online))
        }
    }
}
