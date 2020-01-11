package com.talview.yasma.samsruti.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.talview.yasma.samsruti.domain.Post

@Entity(tableName = "user_posts")
data class DatabasePost constructor(
    @PrimaryKey
    val id : Int,
    val userId : Int,
    val title : String,
    val body : String
)

@Entity(tableName = "user_comments")
data class DatabaseComments constructor(
    @PrimaryKey
    val id : Int,
    val postId : Int,
    val name : String,
    val email : String,
    val body : String
)

@Entity(tableName = "user_albums")
data class DatabaseAlbums constructor(
    @PrimaryKey
    val id : Int,
    val userId : Int,
    val title : String
)

@Entity(tableName = "user_photos")
data class DatabasePhotos constructor(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

//fun List<DatabaseComments>.asDomainModel(): List<Comment>{
//    return map {
//
//    }
//}

//fun List<DatabaseAlbums>.asDomainModel(): List<Comment>{
//    return map {
//
//    }
//}

//fun List<DatabaseAlbums>.asDomainModel(): List<Comment>{
//    return map {
//
//    }
//}
