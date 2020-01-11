package com.talview.yasma.samsruti.ui.posts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talview.yasma.samsruti.database.getDatabase
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class PostsViewModel(app: Application) : ViewModel() {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(app)
    private val postRepository = PostRepository(database)




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

    val allPosts = postRepository.posts

    fun fetchAllPosts(userPosts: List<Post>?) {
        if (userPosts == null){
            _status.value = ApiStatus.ERROR
        } else if(userPosts.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _status.value = ApiStatus.DONE
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PostsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }



}