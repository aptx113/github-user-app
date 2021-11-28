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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danteyu.studio.githubusersapp.data.repository.Repository
import com.danteyu.studio.githubusersapp.model.GitHubUser
import com.danteyu.studio.githubusersapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by George Yu in Nov. 2021.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _gitHubUsersFlow = MutableStateFlow<Resource<List<GitHubUser>>>(Resource.Loading())
    val gitHHubUsersFlow: StateFlow<Resource<List<GitHubUser>>> = _gitHubUsersFlow

    private val _refreshStatusFlow = MutableStateFlow<Boolean>(false)
    val refreshStatusFlow: StateFlow<Boolean> = _refreshStatusFlow

    fun getGitHubUsers() = viewModelScope.launch {
        repository.getGitHubUsersFlow()
            .onCompletion { _refreshStatusFlow.value = false }
            .collect { _gitHubUsersFlow.value = it }
    }

    fun refresh() {
        if (_gitHubUsersFlow.value !is Resource.Loading) {
            getGitHubUsers()
        }
    }
}
