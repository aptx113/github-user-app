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

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.danteyu.studio.githubusersapp.model.GitHubUser
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

/**
 * Created by George Yu in Nov. 2021.
 */
@ProvidedTypeConverter
class GitHubUsersConverter @Inject constructor(private val moshi: Moshi) {

    @TypeConverter
    fun convertGitHubUsersToJson(users: List<GitHubUser>?): String? {
        return users?.let {
            moshi.adapter(List::class.java).toJson(it)
        }
    }

    @TypeConverter
    fun convertJsonToGitHubUsers(json: String?): List<GitHubUser>? {
        return json?.let {
            val type = Types.newParameterizedType(List::class.java, GitHubUser::class.java)
            val adapter: JsonAdapter<List<GitHubUser>> = moshi.adapter(type)
            adapter.fromJson(it)
        }
    }
}
