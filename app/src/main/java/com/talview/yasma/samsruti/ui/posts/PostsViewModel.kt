package com.talview.yasma.samsruti.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


class PostsViewModel(repository: YasmaRepository) : BaseViewModel() {


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
        _status.value = ApiStatus.LOADING
        mainScope.launch {
            try {
                val retriedPosts = repository.getAllPosts()
                fetchAllPosts(retriedPosts)
            } catch (e:Exception){
                Timber.d("Error: $e")
                _status.value = ApiStatus.UNKNOWN_HOST
            }

        }

    }

    val allPosts = repository.posts

    fun fetchAllPosts(userPosts: List<Post>?) {
        if (userPosts == null){
            _status.value = ApiStatus.ERROR
        } else if(userPosts.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _status.value = ApiStatus.DONE
        }
    }



}