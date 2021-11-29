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
package com.danteyu.studio.githubusersapp.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.danteyu.studio.githubusersapp.data.mock.mockGitHubUsers
import com.danteyu.studio.githubusersapp.data.source.db.GitHubUsersDao
import com.danteyu.studio.githubusersapp.data.source.db.GitHubUsersDatabase
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by George Yu in Nov. 2021.
 */
@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class GitHubUsersDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var db: GitHubUsersDatabase
    private lateinit var dao: GitHubUsersDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = db.gitHubUsersDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertGitHubUsers_getUsers() = runBlockingTest {
        dao.insertGitHubUsers(mockGitHubUsers)
        val result = dao.loadGitHubUsersFlow().first()
        Truth.assertThat(result).contains(mockGitHubUsers[0])
    }
}
