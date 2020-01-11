package com.talview.yasma.samsruti.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.util.MainCoroutineRule
import com.talview.yasma.samsruti.util.getOrAwaitValue
import com.talview.yasma.samsruti.util.observeForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PostsViewModelTest{

    // Run tasks synchronously
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fakeData = mutableListOf<Post>(
        Post(11,1,"Title", "Body")
    )

    private lateinit var viewModel: PostsViewModel

    @Before
    fun initViewModel(){
            viewModel = PostsViewModel()
            viewModel.fetchAllPosts(fakeData)
    }

    @Test
    fun getAllPosts_withFakeData_notEmpty(){
        val posts = viewModel.allPosts.getOrAwaitValue()
        assert(posts.isNotEmpty())
    }

    @Test
    fun getAllPosts_withFakeData_status_success(){
        assertEquals(viewModel.status.value, ApiStatus.DONE)
    }

    @Test
    fun getFirstPostItem_withFakeData_success(){
        runBlocking {
            viewModel.allPosts.observeForTesting {
                assertNotNull(viewModel.allPosts.value)
                assertEquals(viewModel.allPosts.value!!.get(0), fakeData[0])
            }
        }

    }
}