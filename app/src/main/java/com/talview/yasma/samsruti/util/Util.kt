package com.talview.yasma.samsruti.util

import android.view.View
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.ApiStatus.*

fun View.setViewVisibility(status: ApiStatus){
    visibility = when (status) {
        LOADING -> {
            View.VISIBLE
        }
        ERROR -> {
            View.VISIBLE
        }
        UNKNOWN_HOST -> {
            View.VISIBLE
        }
        else ->
            View.GONE
    }
}