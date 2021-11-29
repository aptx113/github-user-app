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

import com.danteyu.studio.githubusersapp.data.mock.mockGitHubUsers
import com.danteyu.studio.githubusersapp.data.repository.Repository
import com.danteyu.studio.githubusersapp.utils.Resource
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by George Yu in Nov. 2021.
 */
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK(relaxed = true)
    lateinit var repository: Repository
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getGitHubUsers() {
        mainCoroutineRule.runBlockingTest {
            coEvery { repository.getGitHubUsersFlow() } returns flowOf(
                Resource.Success(
                    mockGitHubUsers
                )
            )
            viewModel.getGitHubUsers()
            val value = viewModel.gitHubUsersFlow.first()
            Truth.assertThat(value.data).isEqualTo(mockGitHubUsers)
            verify { repository.getGitHubUsersFlow() }
        }
    }
}
