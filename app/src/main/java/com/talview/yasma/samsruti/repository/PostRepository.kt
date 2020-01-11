package com.talview.yasma.samsruti.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.talview.yasma.samsruti.database.DatabasePost
import com.talview.yasma.samsruti.database.YasmaDatabase
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.network.YasmaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(private val database: YasmaDatabase) {

    val posts: LiveData<List<Post>> = Transformations.map(database.yasmaDao.getPosts()) { allPosts ->
        allPosts.map {
            Post(id = it.id, userId = it.userId, title = it.title, body = it.body)
        }
    }

    suspend fun getAllPosts(): List<Post>? {
        return withContext(Dispatchers.IO){

            val posts  = YasmaApi.retrofitNetworkService.getAllPosts()

            val databaseModel = posts.map {
                DatabasePost(id = it.id, userId = it.userId, title = it.title, body = it.body)
            }.toTypedArray()

            database.yasmaDao.insertAllPost(*databaseModel)
            posts
        }
    }
}