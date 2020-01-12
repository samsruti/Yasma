package com.talview.yasma.samsruti.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface YasmaDao {
    @Query("SELECT * FROM user_posts")
    fun getPosts(): LiveData<List<DatabasePost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPost(vararg posts: DatabasePost)

    //-------------------------------------------
    @Query("SELECT * FROM user_comments")
    fun getComments(): LiveData<List<DatabaseComments>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComments(vararg posts: DatabaseComments)

    //-------------------------------------------
    @Query("SELECT * FROM user_albums")
    fun getAlbums(): LiveData<List<DatabaseAlbums>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(vararg posts: DatabaseAlbums)

    //-------------------------------------------
    @Query("SELECT * FROM user_photos")
    fun getPhotos(): LiveData<List<DatabasePhotos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhotos(vararg posts: DatabasePhotos)
}