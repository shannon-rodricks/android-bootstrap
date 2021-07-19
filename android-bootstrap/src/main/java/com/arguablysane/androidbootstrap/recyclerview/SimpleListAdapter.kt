package com.arguablysane.androidbootstrap.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arguablysane.androidbootstrap.BR

/**
 * A subclass of [ListAdapter] that enables no-code implementation of RecyclerViews.  Relies on
 * Databinding library to provide all the features.
 *
 * Supports:
 * - Homogenous List - All Items are the same type
 * - Heterogenous List - List of mixed items
 * - Nested Lists - Recyclerview within Recyclerview within RecyclerView
 *
 * Features:
 * - Inflation and binding of list items (uses [BaseListItem.layoutResourceId])
 * - Diff calculation when new list is provided (uses [BaseListItem.itemIdentifier])
 * - RecycledViewPool sharing (needs to be provided to the main recyclerview, might make it autobuild later)
 *
 * Viewmodel Configuration:
 * - Should implement [BaseListItem]
 * - Should define [BaseListItem.layoutResourceId] and [BaseListItem.itemIdentifier]
 *
 * XML Configuration:
 * - [viewModel] - The data variable
 * - [callback] - The click/toggle/whatever handler. Alternately the viewmodel can be used to handle it too if required
 * - [recycledViewPool] - (opt) To utilise a shared [RecyclerView.RecycledViewPool]
 * - [lifeOwner] - (opt) To pass lifecycleOwner from the parent to the child (WIP to remove this reqt)
 *
 * @property lifecycleOwner The lifecycle owner for the view. Mainly used to pass it to child views.
 * This lets it react to changes in a livedata variable
 * @property callback User interaction callback handler. If a heterogenous/nested list, this should be a merged interface of all the handler interfaces
 * @property recycledViewPool (OPTIONAL) A recyclerview pool to generate child views from.
 * This is particularly beneficial for nested recyclerviews, reducing the amount of similar views generated across multiple recyclerviews
 * @property hasStableIds Do the children have stable identifiers?
 * @property diffUtilCallback (OPTIONAL) If you wish to provide your own diff util callback. The default one compares items using [BaseListItem.itemIdentifier]
 */
open class SimpleListAdapter<T : BaseListItem>(
    private val lifecycleOwner: LifecycleOwner? = null,
    private val callback: Any? = null,
    private val recycledViewPool: RecyclerView.RecycledViewPool? = null,
    hasStableIds: Boolean = true,
    diffUtilCallback: DiffUtil.ItemCallback<T>? = null,
) : ListAdapter<T, ViewDatabindingHolder>(
    diffUtilCallback ?: object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.itemIdentifier == newItem.itemIdentifier

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.itemIdentifier == newItem.itemIdentifier
    }
) {

    init {
        setHasStableIds(hasStableIds)
    }

    override fun getItemId(position: Int): Long = getItem(position).itemIdentifier

    override fun getItemViewType(position: Int): Int = getItem(position).layoutResourceId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewDatabindingHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )

        binding.setVariable(BR.callback, callback)
        binding.setVariable(BR.recycledViewPool, recycledViewPool)
        binding.setVariable(BR.lifeOwner, lifecycleOwner)

        binding.lifecycleOwner = lifecycleOwner

        return ViewDatabindingHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewDatabindingHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, getItem(position))
        holder.binding.executePendingBindings()
    }
}