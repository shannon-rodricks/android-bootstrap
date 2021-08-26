package com.arguablysane.androidbootstrap.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [RecyclerView.ViewHolder] for a [ViewDataBinding]
 */
open class ViewDatabindingHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root)