package com.talview.yasma.samsruti.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


enum class ApiStatus {LOADING, DONE, ERROR}

@Parcelize
data class Post(
    val userId : Int,
    val id : Int,
    val title : String,
    val body : String) : Parcelable

@Parcelize
data class Comment(
    val postId : Int,
    val id : Int,
    val name : String,
    val email : String,
    val body : String
): Parcelable


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
