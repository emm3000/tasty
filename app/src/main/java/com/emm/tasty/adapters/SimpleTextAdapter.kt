package com.emm.tasty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emm.tasty.databinding.ItemSimpleBinding

class SimpleTextAdapter(
    private val list: List<String>
) : RecyclerView.Adapter<SimpleTextAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSimpleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = list[position]
        holder.binding.tvSimpleText.text = item
    }

    override fun getItemCount(): Int = list.size

}