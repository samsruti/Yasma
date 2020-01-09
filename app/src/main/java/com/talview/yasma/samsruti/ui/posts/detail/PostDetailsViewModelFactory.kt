package com.talview.yasma.samsruti.ui.posts.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.ui.albums.detail.PostDetailsViewModel
import java.lang.IllegalArgumentException

class PostDetailsViewModelFactory(
    private val currentPost: Post,
    private val application: Application
): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostDetailsViewModel::class.java)){
                return PostDetailsViewModel(currentPost, application) as T
            }
            throw IllegalArgumentException("Unknow ViewModel Class")
        }
    }
