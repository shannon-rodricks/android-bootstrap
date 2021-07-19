package com.arguablysane.androidbootstrap.recyclerview

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Databinding Adapters for [RecyclerView]
 *
 * Provides:
 * - [setRecyclerviewList] - No-code RecyclerView adapter builder
 */
object RecyclerViewBindingAdapter {

    @JvmStatic
    @BindingAdapter(
        value = [
            "recyclerview_list",
            "recyclerview_createAdapter",
            "recyclerview_diffutil",
            "recyclerview_callback",
            "recyclerview_recycledViewPool",
            "recyclerview_hasStableIds",
            "lifecycleOwner"
        ],
        requireAll = false
    )
    fun <T : BaseListItem> RecyclerView.setRecyclerviewList(
        list: List<T>?,
        listCreateAdapter: Boolean = true,
        diffUtilItemCallback: DiffUtil.ItemCallback<T>? = null,
        listCallback: Any? = null,
        recycledViewPool: RecyclerView.RecycledViewPool? = null,
        hasStableIds : Boolean = true,
        lifecycleOwner: LifecycleOwner?
    ) {
        val lifeOwner = lifecycleOwner ?: ViewTreeLifecycleOwner.get(this)

        this.setRecycledViewPool(recycledViewPool)

        if (list != null) {
            var adapter = (this.adapter as? SimpleListAdapter<T>)
            if (adapter == null && listCreateAdapter) {
                adapter = SimpleListAdapter(
                    callback = listCallback,
                    lifecycleOwner = lifeOwner,
                    diffUtilCallback = diffUtilItemCallback,
                    recycledViewPool = recycledViewPool,
                    hasStableIds = hasStableIds
                )
                this.adapter = adapter
            }

            adapter?.submitList(list)
        }
    }

}