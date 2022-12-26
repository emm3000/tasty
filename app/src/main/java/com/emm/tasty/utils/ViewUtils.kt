package com.emm.tasty.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    abstract fun isLoading(): Boolean
    abstract fun loadMoreItems()
    abstract fun hideKeyboardOnScroll()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!isLoading()) {
            if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                loadMoreItems()
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        hideKeyboardOnScroll()
    }
}

inline fun RecyclerView.infinityScroll(
    crossinline isLoading: () -> Boolean = { false },
    crossinline loadMoreItems: () -> Unit = {},
    hideKeyboardOnScroll: Boolean = false
): PaginationScrollListener {
    val listener = object : PaginationScrollListener(layoutManager as LinearLayoutManager) {
        override fun isLoading(): Boolean = isLoading()
        override fun loadMoreItems() = loadMoreItems()
        override fun hideKeyboardOnScroll() {
            if (hideKeyboardOnScroll) hideKeyboard()
        }
    }
    addOnScrollListener(listener)
    return listener
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}