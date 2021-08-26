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

    /**
     * @param list A list to display. Can also be a MutableLiveData<List>
     * @param listCreateAdapter Whether or not to create an adapter. False if the screen wants to provide a custom adapter
     * @param diffUtilItemCallback Optional, An instance of DiffUtil.ItemCallback. If one is not provided, a default one is created that compares the identifiers
     * @param listCallback Callback for when the user clicks on the items in a list. Incase the list is a mixed list, an amalgamted listener will need to be provided
     * @param recycledViewPool A RecyclerView.RecycledViewPool to use. This is required if you wish to be efficient when dealing with multiple lists in the same screen, having similar items
     * @param hasStableIds Whether the list has stable ids or not
     * @param
     */
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