package com.talview.yasma.samsruti.ui.albums.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.PostCommentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsViewModel(
    currentPost: Post,
    app: Application
) : AndroidViewModel(app) {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val postCommentRepository = PostCommentRepository()

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status



    private val _selectedPost = MutableLiveData<Post>()
    val selectedPost: LiveData<Post>
        get() = _selectedPost

    private val _allComments = MutableLiveData<List<Comment>>()
    val allComments: LiveData<List<Comment>>
        get() = _allComments

    init {
        _selectedPost.value = currentPost
        uiCoroutineScope.launch {
            _status.value = ApiStatus.LOADING
            fetchAllComments(postCommentRepository.getAllComments(currentPost.id))
        }


    }
    fun fetchAllComments(allComments: List<Comment>?){
        if (allComments == null){
            _status.value = ApiStatus.ERROR
        } else if(allComments.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _allComments.value = allComments
            _status.value = ApiStatus.DONE
        }
    }


}
