package com.talview.yasma.samsruti.ui.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.PostRepository
import kotlinx.coroutines.*


class PostsViewModel(application: Application) : AndroidViewModel(application) {

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
            _allPosts.value = postRepository.getAllPosts()
        }
    }



    private fun getAllPostsFromNetwork() {



//        uiCoroutineScope.launch {
//            _status.value = ApiStatus.LOADING
//
//            try {
//                val getAllPostResponse = getAllPosts()
//                _status.value = ApiStatus.DONE
//                _allPosts.value = getAllPostResponse.
//
//            } catch (e: Exception){
//                _status.value = ApiStatus.ERROR
//                _allPosts.value = ArrayList()
//
//            }
//        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}