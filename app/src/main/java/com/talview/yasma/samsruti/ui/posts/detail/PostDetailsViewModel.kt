package com.talview.yasma.samsruti.ui.posts.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.talview.yasma.samsruti.database.getDatabase
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.PostCommentRepository
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsViewModel(
    currentPost: Post,
    app: Application
) : AndroidViewModel(app) {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(app)
    private val postCommentRepository = PostCommentRepository(database)

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

        uiCoroutineScope.launch {

            _status.value = ApiStatus.LOADING

            async {
                postCommentRepository.getAllComments(currentPost.id)
            }.await()
        }


    }


    class Factory(
        private val currentPost: Post,
        private val application: Application
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostDetailsViewModel::class.java)) {
                return PostDetailsViewModel(currentPost, application) as T
            }
            throw IllegalArgumentException("Unknow ViewModel Class")
        }
    }

}
