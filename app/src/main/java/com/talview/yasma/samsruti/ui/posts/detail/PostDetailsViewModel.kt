package com.talview.yasma.samsruti.ui.albums.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.Comment
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

    private val _allComments = MutableLiveData<List<Comment>>()
    val allComments: LiveData<List<Comment>>
        get() = _allComments

    init {
        _selectedPost.value = currentPost
        val list = mutableListOf(
            Comment(1,1,"sam", "sam@sam.com", "hey! this is body"),
            Comment(1,2,"sam 2 ", "sam@sam.com", "hey! this is body 2 ")
        )
        _allComments.value = list
    }



}
