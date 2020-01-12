package com.talview.yasma.samsruti.network

import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.domain.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface YasmaApiService {
    //    Get all posts
    @GET("posts")
    suspend fun getAllPosts(): List<Post>

    //    Post comments
    @GET("comments")
    suspend fun getPostComments(
        @Query("postId") postId: Int
    ) : List<Comment>

    //    Get all albums
    @GET("albums")
    suspend fun getAllAlbums(): List<Album>

    //    Get all photos from each album
    @GET("photos")
    suspend fun getAlbumPhotos(
        @Query("albumId") albumId: Int
    ): List<Photo>
}