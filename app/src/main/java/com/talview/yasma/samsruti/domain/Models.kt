package com.talview.yasma.samsruti.domain

data class Post(
    val userId : Int,
    val id : Int,
    val title : String,
    val body : String)

data class Comment(
    val postId : Int,
    val id : Int,
    val name : String,
    val email : String,
    val body : String
)

data class Album(
    val userId : Int,
    val id : Int,
    val title : String
)

data class Photos(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
