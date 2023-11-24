package com.example.hotelproject.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelproject.databinding.ItemDropRoomBinding
import com.example.hotelproject.models.RoomModelUI
import com.example.hotelproject.presentation.base.BaseDiffUtilItemCallback

class RoomAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<RoomModelUI, RoomAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    inner class ViewHolder(private val binding: ItemDropRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.chooseNumberButton.setOnClickListener {
                onItemClick.invoke(adapterPosition)
            }
        }

        fun onBind(item: RoomModelUI?) = with(binding) {
            val peculiaritiesAdapter = PeculiaritiesAdapter()
            val collageAdapter = ImageCollageAdapter()
            binding.itemDropRecView.adapter = peculiaritiesAdapter
            binding.itemCollageRecView.adapter = collageAdapter
            itemDropDescription.text = item?.name
            item?.peculiarities?.let { peculiaritiesAdapter.setList(it) }
            collageAdapter.submitList(item?.imageUrls)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDropRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}