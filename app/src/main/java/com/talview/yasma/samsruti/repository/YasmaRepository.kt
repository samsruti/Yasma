package com.talview.yasma.samsruti.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.talview.yasma.samsruti.database.*
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.network.YasmaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject




class PostCommentRepository(private val database: YasmaDatabase) {

    val allComments: LiveData<List<Comment>> = Transformations.map(database.yasmaDao.getComments()) { dbComments ->
        dbComments.map {
            Comment(id = it.id, postId = it.postId ,email = it.email ,name = it.name , body = it.body )
        }
    }

    suspend fun getAllComments(postId: Int){
        return withContext(Dispatchers.IO){

            val comments  = YasmaApi.retrofitNetworkService.getPostComments(postId)

            val databaseModel = comments.map {
                DatabaseComments(id = it.id, postId = it.postId ,email = it.email ,name = it.name , body = it.body )
            }.toTypedArray()

            database.yasmaDao.insertAllComments(*databaseModel)
            
        }
    }
}

class AlbumRepository(private val database: YasmaDatabase) {

    val albums: LiveData<List<Album>> = Transformations.map(database.yasmaDao.getAlbums()) { allAlbums ->
       allAlbums.map {
           Album(id = it.id, userId = it.userId, title = it.title)
       }
    }

    suspend fun getAllAlbums(): List<Album>? {
        return withContext(Dispatchers.IO){

            val albums  = YasmaApi.retrofitNetworkService.getAllAlbums()

            val databaseModel = albums.map {
                DatabaseAlbums(id = it.id, userId = it.userId, title = it.title)
            }.toTypedArray()

            database.yasmaDao.insertAllAlbums(*databaseModel)
            albums
        }
    }
}


class AlbumPhotosRepository(private val database: YasmaDatabase) {

    suspend fun getAllPhotos(albumId: Int): List<Photo>? {
        return withContext(Dispatchers.IO){

            val photos  = YasmaApi.retrofitNetworkService.getAlbumPhotos(albumId)

            val databaseModel = photos.map {
                DatabasePhotos(id = it.id, albumId = it.albumId, thumbnailUrl = it.thumbnailUrl,title = it.title,url = it.url)
            }.toTypedArray()

            database.yasmaDao.insertAllPhotos(*databaseModel)
            photos
        }
    }
}

