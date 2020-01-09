package com.talview.yasma.samsruti.ui.albums.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.Post

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsViewModel(
    currentPost: Post,
    app: Application
) : AndroidViewModel(app) {

    private val _selectedPost = MutableLiveData<Post>()
    val selectedPost: LiveData<Post>
        get() = _selectedPost

    init {
        _selectedPost.value = currentPost
    }

}
