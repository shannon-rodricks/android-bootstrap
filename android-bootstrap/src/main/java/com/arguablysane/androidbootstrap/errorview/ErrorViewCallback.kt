package com.arguablysane.androidbootstrap.errorview

/**
 * Interface to handle when the user clicks on the retry button in [R.layout.widget_errorview]
 */
interface ErrorViewCallback {

    fun onErrorViewRetryButtonClicked(errorViewState: ErrorViewState)

}