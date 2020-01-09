package com.talview.yasma.samsruti.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post


class PostsViewModel : ViewModel() {


    private val _allPosts = MutableLiveData<List<Post>>()
    val allPosts: LiveData<List<Post>>
        get() = _allPosts

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _navigateToSelectedPost = MutableLiveData<Post>()
    val navigateToSelectedPost: LiveData<Post>
        get() = _navigateToSelectedPost

    fun displayPostDetails(post: Post) {
        _navigateToSelectedPost.value = post
    }

    fun displayPostDetailsComplete() {
        _navigateToSelectedPost.value = null
    }

    init {

        val list = mutableListOf<Post>(
            Post(1,1,"title1","body1"),
            Post(1,3,"title2","body2"),
            Post(1,5,"title3","body3")
        )
        _allPosts.value = list
    }

}