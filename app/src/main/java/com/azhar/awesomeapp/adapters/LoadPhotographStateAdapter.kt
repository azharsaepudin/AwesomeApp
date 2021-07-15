package com.azhar.awesomeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azhar.awesomeapp.databinding.ItemLoadingBinding
import com.google.android.material.button.MaterialButton

class LoadPhotographStateAdapter(private val retry:()->Unit): LoadStateAdapter<LoadPhotographStateAdapter.LoadingViewHolder>() {


    class LoadingViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {

        private val retry: MaterialButton = binding.btnTry
            .also {
                it.setOnClickListener { retry() }
            }

        fun bind(loadState: LoadState) {

            with(binding){
                if (loadState is LoadState.Error){
                    tvErrorMessage.text = loadState.error.localizedMessage
                }

                progressBar.isVisible = loadState is LoadState.Loading
                retry.isVisible = loadState is LoadState.Error
                tvErrorMessage.isVisible = loadState is LoadState.Error
            }
        }

    }

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) = holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingViewHolder(ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false), retry)


}