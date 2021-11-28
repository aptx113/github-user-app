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
package com.danteyu.studio.githubusersapp.utils

import com.danteyu.studio.githubusersapp.R
import com.danteyu.studio.githubusersapp.ext.stringSuspending
import com.danteyu.studio.githubusersapp.model.ErrorResponse
import com.danteyu.studio.githubusersapp.utils.NetworkUtil.hasInternetConnection
import com.danteyu.studio.githubusersapp.utils.Utils.getString
import com.squareup.moshi.Moshi
import retrofit2.HttpException

/**
 * Created by George Yu in Nov. 2021.
 */
@SuppressWarnings("TooGenericExceptionCaught")
suspend inline fun <T> networkBoundResource(
    crossinline apiCall: suspend () -> T,
    crossinline saveApiCall: suspend (T) -> Unit
): Resource<T> = if (hasInternetConnection()) {
    try {
        saveApiCall(apiCall.invoke())
        Resource.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        if (throwable is HttpException) {
            val code = throwable.code()
            val errorResponse = convertErrorBody(throwable)
            Resource.GenericError(code, errorResponse)
        } else {
            Resource.Error("Error with $throwable")
        }
    }
} else {
    Resource.Error(getString(R.string.no_internet_connection))
}

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun convertErrorBody(throwable: HttpException): ErrorResponse? =
    throwable.response()?.errorBody()?.let {
        val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
        moshiAdapter.fromJson(it.stringSuspending())
    }
