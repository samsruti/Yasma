package com.talview.yasma.samsruti.ui.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.network.YasmaApi
import com.talview.yasma.samsruti.repository.PostRepository
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception
import java.util.ArrayList


class PostsViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


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
        _allPosts.value = emptyList()

        getAllPostsFromNetwork()


    }

    private suspend fun getAllPosts() = YasmaApi.retrofitNetworkService.getAllPosts()

//    private val postRepository = PostRepository()

    private fun getAllPostsFromNetwork() {

//        uiCoroutineScope.launch {
//            postRepository.refreshAllPosts()
//        }

        uiCoroutineScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                val getAllPostResponse = getAllPosts()
                if (getAllPostResponse.isSuccessful && getAllPostResponse.body() != null){
                    _status.value = ApiStatus.DONE
                    _allPosts.value = getAllPostResponse.body()
                } else {

                    _status.value = ApiStatus.UNSUCCESSFUL
                    _allPosts.value = ArrayList()
                }
            } catch (e: Exception){
                _status.value = ApiStatus.ERROR
                _allPosts.value = ArrayList()

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}