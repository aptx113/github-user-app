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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.danteyu.studio.githubusersapp.common.SingleFieldDiffUtils
import com.danteyu.studio.githubusersapp.databinding.ItemMainOneBinding
import com.danteyu.studio.githubusersapp.databinding.ItemMainTwoBinding
import com.danteyu.studio.githubusersapp.model.GitHubUser

/**
 * Created by George Yu in Nov. 2021.
 */
class MainAdapter : ListAdapter<GitHubUser, MainViewHolder<*>>(SingleFieldDiffUtils { it.id }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        @LayoutRes viewType: Int
    ): MainViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)

        return when (binding) {
            is ItemMainOneBinding -> MainOneViewHolder(binding)
            is ItemMainTwoBinding -> MainTwoViewHolder(binding)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder<*>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) R.layout.item_main_one
        else R.layout.item_main_two
    }
}
