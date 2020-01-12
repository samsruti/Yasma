package com.talview.yasma.samsruti.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.talview.yasma.samsruti.database.*
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.network.YasmaApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YasmaRepository (
    private val networkService: YasmaApiService,
    private val yasmaDao: YasmaDao)

{

    val posts: LiveData<List<Post>> = Transformations.map(yasmaDao.getPosts()) { allPosts ->
        allPosts.map {
            Post(id = it.id, userId = it.userId, title = it.title, body = it.body)
        }
    }

    val allComments: LiveData<List<Comment>> = Transformations.map(yasmaDao.getComments()) { dbComments ->
        dbComments.map {
            Comment(id = it.id, postId = it.postId ,email = it.email ,name = it.name , body = it.body )
        }
    }

    val albums: LiveData<List<Album>> = Transformations.map(yasmaDao.getAlbums()) { allAlbums ->
        allAlbums.map {
            Album(id = it.id, userId = it.userId, title = it.title)
        }
    }

    val photos: LiveData<List<Photo>> = Transformations.map(yasmaDao.getPhotos()) { allPhotos ->
        allPhotos.map {
            Photo(id = it.id, albumId = it.albumId, thumbnailUrl = it.thumbnailUrl,title = it.title,url = it.url)
        }
    }



    suspend fun getAllPosts(): List<Post>? {
        return withContext(Dispatchers.IO){

            val posts  = networkService.getAllPosts()

            val databaseModel = posts.map {
                DatabasePost(id = it.id, userId = it.userId, title = it.title, body = it.body)
            }.toTypedArray()

            yasmaDao.insertAllPost(*databaseModel)

            posts
        }
    }

    suspend fun getAllComments(postId: Int){
        return withContext(Dispatchers.IO){

            val comments  = networkService.getPostComments(postId)

            val databaseModel = comments.map {
                DatabaseComments(id = it.id, postId = it.postId ,email = it.email ,name = it.name , body = it.body )
            }.toTypedArray()

            yasmaDao.insertAllComments(*databaseModel)

        }
    }

    suspend fun getAllAlbums(): List<Album>? {
        return withContext(Dispatchers.IO){

            val albums  = networkService.getAllAlbums()

            val databaseModel = albums.map {
                DatabaseAlbums(id = it.id, userId = it.userId, title = it.title)
            }.toTypedArray()

            yasmaDao.insertAllAlbums(*databaseModel)
            albums
        }
    }

    suspend fun getAllPhotos(albumId: Int): List<Photo>? {
        return withContext(Dispatchers.IO){

            val photos  = networkService.getAlbumPhotos(albumId)

            val databaseModel = photos.map {
                DatabasePhotos(id = it.id, albumId = it.albumId, thumbnailUrl = it.thumbnailUrl,title = it.title,url = it.url)
            }.toTypedArray()

            yasmaDao.insertAllPhotos(*databaseModel)
            photos
        }
    }

}