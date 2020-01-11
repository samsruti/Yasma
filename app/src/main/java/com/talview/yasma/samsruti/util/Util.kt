package com.talview.yasma.samsruti.util

import android.view.View
import com.talview.yasma.samsruti.domain.ApiStatus

fun View.setViewVisibility(status: ApiStatus){
    when (status) {
        ApiStatus.LOADING-> {
            visibility = View.VISIBLE
        }
        ApiStatus.ERROR -> {
            visibility = View.VISIBLE
        }
        ApiStatus.DONE -> {
            visibility = View.GONE
        }
        ApiStatus.UNSUCCESSFUL -> {
            visibility = View.GONE
        }

    }
}