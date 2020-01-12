package com.talview.yasma.samsruti.ui.posts.detail


import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsViewModel(postCommentRepository:YasmaRepository, val currentPost: Post) : BaseViewModel(){


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status



    private val _selectedPost = MutableLiveData<Post>()
    val selectedPost: LiveData<Post>
        get() = _selectedPost



    val allComments = postCommentRepository.allComments

    init {

        _selectedPost.value = currentPost

        if (allComments.value == null){
            _status.value = ApiStatus.ERROR
        } else if(allComments.value!!.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _status.value = ApiStatus.DONE
        }

        mainScope.launch {

            _status.value = ApiStatus.LOADING
            postCommentRepository.getAllComments(currentPost.id)

        }


    }

}
