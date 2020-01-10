package com.talview.yasma.samsruti.ui.albums.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.PostCommentRepository
import com.talview.yasma.samsruti.repository.PostRepository
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




    private val _selectedPost = MutableLiveData<Post>()
    val selectedPost: LiveData<Post>
        get() = _selectedPost

    private val _allComments = MutableLiveData<List<Comment>>()
    val allComments: LiveData<List<Comment>>
        get() = _allComments

    init {
        _selectedPost.value = currentPost
        uiCoroutineScope.launch {
            _allComments.value = postCommentRepository.getAllComments(currentPost.id)
        }

    }



}
