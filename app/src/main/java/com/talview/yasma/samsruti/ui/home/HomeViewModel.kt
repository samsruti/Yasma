package com.talview.yasma.samsruti.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "YASMA App by Samsruti"
    }
    val text: LiveData<String> = _text
}