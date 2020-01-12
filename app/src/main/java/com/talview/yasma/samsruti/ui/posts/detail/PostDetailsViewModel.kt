package com.talview.yasma.samsruti.ui.posts.detail


import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

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

        mainScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                postCommentRepository.getAllComments(currentPost.id)
                fetchAll(allComments.value)
            } catch (e:Exception){
                Timber.d("Error: $e")
                _status.value = ApiStatus.UNKNOWN_HOST
            }

        }

    }

    fun fetchAll(comments: List<Comment>?) {
        if (comments == null){
            _status.value = ApiStatus.ERROR
        } else if(comments.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _status.value = ApiStatus.DONE
        }
    }

}
