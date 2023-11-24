package com.example.hotelproject.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelproject.databinding.ItemPeculiaritiesBinding

class PeculiaritiesAdapter : RecyclerView.Adapter<PeculiaritiesAdapter.ViewHolder>() {

    private var list: ArrayList<String> = ArrayList()

    fun setList(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPeculiaritiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(s: String) {
            binding.itemPeculiarButton.text = s
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPeculiaritiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}