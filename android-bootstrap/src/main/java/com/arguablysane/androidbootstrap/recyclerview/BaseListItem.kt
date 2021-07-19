package com.arguablysane.androidbootstrap.recyclerview

/**
 * A component of the [SimpleListAdapter]
 *
 * This needs to be implemented in item viewmodels that wish to use the adapter
 */
interface BaseListItem {

    /**
     * Return a unique identifier for the list item.
     * - Should not clash with the other elements in the list
     * - Should be consistent across multiple instances of the list
     *
     * Ideally, a [Long] representation of the id
     */
    val itemIdentifier : Long

    /**
     * The resource identifier for the layout required to render this item
     * The xml should ideally have variables defined as so, if required:
     * [viewModel] The data component
     * [callback] The handler component
     */
    val layoutResourceId : Int


}