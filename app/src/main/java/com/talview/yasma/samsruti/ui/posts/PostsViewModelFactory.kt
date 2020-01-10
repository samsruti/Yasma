package com.talview.yasma.samsruti.ui.posts

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.ui.albums.detail.PostDetailsViewModel
import java.lang.IllegalArgumentException

class PostsViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostsViewModel::class.java)){
                return PostsViewModel(application) as T
            }
            throw IllegalArgumentException("Unknow ViewModel Class")
        }
    }
