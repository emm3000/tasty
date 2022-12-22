package com.emm.tasty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emm.tasty.databinding.ItemRecipeBinding
import com.emm.tasty.models.RecipeModel

class RecipeAdapter : ListAdapter<RecipeModel, RecipeAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeModel) {
            binding.recipeModel = recipe
        }
    }

    companion object {
        private var DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecipeModel>() {
            override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean =
                oldItem == newItem

        }
    }


}