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
package com.danteyu.studio.githubusersapp.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danteyu.studio.githubusersapp.model.GitHubUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by George Yu in Nov. 2021.
 */
@Dao
interface GitHubUsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGitHubUsers(gitHubUser: GitHubUser)

    @Query("SELECT * FROM gitHubUsers_table")
    fun loadGitHubUsersFlow(): Flow<List<GitHubUser>>
}
