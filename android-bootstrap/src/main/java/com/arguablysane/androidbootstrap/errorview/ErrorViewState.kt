package com.arguablysane.androidbootstrap.errorview

import androidx.annotation.DrawableRes

/**
 * State object for [R.layout.widget_errorview]
 * @param id Optional. This could beused to differentiate between different errors, if special handling is required.
 * @param iconResourceId Optional. The resource id for an icon to display. If null, no icon is displayed
 * @param title Optional. The title to display. If null, no title is displayed.
 * @param message Optional. Message to display. If null, no message is displayed.
 * @param retryBtnLabel Optional. The label to display on the retry button. If null, no button is displayed
 */
data class ErrorViewState(
    val id: Int? = null,
    @DrawableRes val iconResourceId: Int? = null,
    val title: CharSequence? = null,
    val message: CharSequence? = null,
    val retryBtnLabel: CharSequence? = null
)