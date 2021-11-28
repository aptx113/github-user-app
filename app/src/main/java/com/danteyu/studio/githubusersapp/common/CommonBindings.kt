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
package com.danteyu.studio.githubusersapp.common

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.danteyu.studio.githubusersapp.CROSS_FADE_IN_MILLIS
import com.danteyu.studio.githubusersapp.R
import com.danteyu.studio.githubusersapp.model.GitHubUser
import com.danteyu.studio.githubusersapp.utils.Resource

/**
 * Created by George Yu in Nov. 2021.
 */
object CommonBindings {

    @BindingAdapter("imgUrl")
    @JvmStatic
    fun bindImageFromUrl(imageView: ImageView, imgUrl: String) =
        imageView.load(imgUrl) {
            crossfade(CROSS_FADE_IN_MILLIS)
            error(R.drawable.img_placeholder)
        }

    @BindingAdapter("apiResponse")
    @JvmStatic
    fun bindErrorVisibility(view: View, apiResponse: Resource<List<GitHubUser>>) {
        when (view) {
            is ProgressBar -> view.isVisible = apiResponse is Resource.Loading
        }
    }
}
