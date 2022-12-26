package com.emm.tasty.databindingutils

import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emm.tasty.R
import com.emm.tasty.adapters.RecipeAdapter
import com.emm.tasty.adapters.SimpleTextAdapter
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
    items?.let {
        (rv.adapter as? RecipeAdapter)?.submitList(it)
    }
}

@BindingAdapter("app:detailsList")
fun setDetailListAdapter(rv: RecyclerView, items: List<String>?) {
    items?.let {
        rv.adapter = SimpleTextAdapter(it)
    }
}

@BindingAdapter("app:value", "app:label")
fun setDataMapItem(tv: TextView, value: String?, label: String?) {
    if (value != null && label != null) {
        val showInfoText = SpannableStringBuilder()
            .bold { append(label) }
            .append(" ")
            .append(value)
        tv.text = showInfoText
    }
}