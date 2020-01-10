package com.talview.yasma.samsruti.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.domain.Post
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface YasmaApiService {

//    Get all posts
    @GET("posts")
    suspend fun getAllPosts(): List<Post>

//        Post details
    @GET("posts/{id}")
    suspend fun getPostDetails(@Path("id") postId: Int): Post

    //    Post comments
    @GET("posts/{id}/comments")
    suspend fun getPostComments(@Path("id") postId: Int): List<Comment>

//-------------------------------------------------------------------------------

//    Get all albums
    @GET("albums")
    suspend fun getAllAlbums(): List<Album>

//    Get all photos from each album
    @GET("albums/{id}")
    suspend fun getAlbumDetails(@Path("id") albumId: Int): Album

//    Get all photos from each album
    @GET("albums/{id}/photos")
    suspend fun getAlbumPhotos(@Path("id") albumId: Int): List<Photo>

}


object YasmaApi {
    val retrofitNetworkService: YasmaApiService by lazy {
        retrofit.create(YasmaApiService::class.java)
    }
}


