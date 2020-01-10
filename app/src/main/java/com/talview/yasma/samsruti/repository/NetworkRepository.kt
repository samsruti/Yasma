package com.talview.yasma.samsruti.repository

import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.network.YasmaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository() {

    suspend fun getAllPosts(): List<Post> {
        return withContext(Dispatchers.IO){
            YasmaApi.retrofitNetworkService.getAllPosts()
        }
    }
}

class PostCommentRepository() {

    suspend fun getAllComments(postId: Int): List<Comment> {
        return withContext(Dispatchers.IO){
            YasmaApi.retrofitNetworkService.getPostComments(postId)
        }
    }
}

class AlbumRepository() {

    suspend fun getAllAlbums(): List<Album> {
        return withContext(Dispatchers.IO){
            YasmaApi.retrofitNetworkService.getAllAlbums()
        }
    }
}

class AlbumPhotosRepository() {

    suspend fun getAllPhotos(albumId: Int): List<Photo> {
        return withContext(Dispatchers.IO){
            YasmaApi.retrofitNetworkService.getAlbumPhotos(albumId)
        }
    }
}

