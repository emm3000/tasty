package com.emm.tasty.databindingutils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emm.tasty.adapters.RecipeAdapter
import com.emm.tasty.models.RecipeModel

@BindingAdapter("app:url")
fun setImageViewUrl(view: ImageView, url: String?) {
    url?.let {
        view.load(it) {
            crossfade(true)
            crossfade(1000)
        }

    }
}

@BindingAdapter("app:recipeList")
fun setRecipeListAdapter(rv: RecyclerView, items: List<RecipeModel>?) {
    (rv.adapter as? RecipeAdapter)?.submitList(items ?: emptyList())
        ?: run {
            rv.adapter = RecipeAdapter().apply {
                submitList(items ?: emptyList())
            }
        }

}