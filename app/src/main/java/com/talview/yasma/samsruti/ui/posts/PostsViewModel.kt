package com.talview.yasma.samsruti.ui.posts

import android.app.Application
import androidx.lifecycle.*
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.PostRepository
import kotlinx.coroutines.*


class PostsViewModel() : ViewModel() {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val postRepository = PostRepository()



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
        uiCoroutineScope.launch {
            _status.value = ApiStatus.LOADING
            fetchAllPosts(postRepository.getAllPosts())
        }

    }

    fun fetchAllPosts(userPosts: List<Post>?) {
        if (userPosts == null){
            _status.value = ApiStatus.ERROR
        } else if(userPosts.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _allPosts.value = userPosts
            _status.value = ApiStatus.DONE
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}