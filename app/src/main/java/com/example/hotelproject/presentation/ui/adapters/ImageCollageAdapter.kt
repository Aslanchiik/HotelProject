package com.example.hotelproject.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelproject.databinding.ItemCollageImageBinding

class ImageCollageAdapter :
    ListAdapter<String, ImageCollageAdapter.ViewHolder>(diffUtilItemCallback) {

    class ViewHolder(private val binding: ItemCollageImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String?) {
            Glide.with(binding.itemCollageImage).load(item)
                .into(binding.itemCollageImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCollageImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        val diffUtilItemCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}