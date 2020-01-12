package com.talview.yasma.samsruti.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import java.net.HttpURLConnection


@RunWith(JUnit4::class)
class PostsYasmaRepositoryTest : BaseRepositoryMockTest() {

    @Test
    fun search_recipes_result_ok() {
        mockHttpResponse("posts.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val mockPosts = yasmaRepository.getAllPosts()
            assertNotNull(mockPosts)
            assertEquals(mockPosts.isNullOrEmpty(), false)
        }
    }
//
    @Test
    fun single_post_result_valid() {
        mockHttpResponse("posts.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val allPostsMocked = yasmaRepository.getAllPosts()
            assertNotNull(allPostsMocked!!)
            assertEquals(allPostsMocked.isNullOrEmpty(), false)
            val singlePost = allPostsMocked[0]
            assertEquals(singlePost.userId, 1)
            assertEquals(singlePost.id, 1)
            assertEquals(singlePost.title, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
            assertEquals(singlePost.body, "quia et suscipit\n" +
                    "suscipit recusandae consequuntur expedita et cum\n" +
                    "reprehenderit molestiae ut ut quas totam\n" +
                    "nostrum rerum est autem sunt rem eveniet architecto")
        }
    }
//
    @Test(expected = HttpException::class)
    fun response_network_error() {
        mockHttpResponse("posts.json", HttpURLConnection.HTTP_BAD_REQUEST)
        runBlocking {
            val mockedPosts = yasmaRepository.getAllPosts()
            assertEquals(mockedPosts.isNullOrEmpty(), true)
        }
    }


}