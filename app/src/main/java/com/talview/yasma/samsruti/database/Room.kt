package com.talview.yasma.samsruti.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface YasmaDao {
    @Query ("SELECT * FROM user_posts")
    fun getPosts(): LiveData<List<DatabasePost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPost(vararg posts: DatabasePost)

//-------------------------------------------
    @Query ("SELECT * FROM user_comments")
    fun getComments(): LiveData<List<DatabaseComments>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComments(vararg posts: DatabaseComments)

    //-------------------------------------------
    @Query ("SELECT * FROM user_albums")
    fun getAlbums(): LiveData<List<DatabaseAlbums>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(vararg posts: DatabaseAlbums)

    //-------------------------------------------
    @Query ("SELECT * FROM user_photos")
    fun getPhotos(): LiveData<List<DatabasePhotos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhotos(vararg posts: DatabasePhotos)


}

@Database(entities = [DatabasePost::class, DatabaseAlbums::class, DatabaseComments::class, DatabasePhotos::class],
    version = 1, exportSchema = false)

abstract class YasmaDatabase: RoomDatabase() {
    abstract val yasmaDao: YasmaDao
}

private lateinit var DATABASE_INSTANCE: YasmaDatabase

fun getDatabase(context: Context): YasmaDatabase {
    synchronized(YasmaDatabase::class.java) {
        if(!::DATABASE_INSTANCE.isInitialized) {
            DATABASE_INSTANCE =
                Room.databaseBuilder(
                    context.applicationContext,
                    YasmaDatabase::class.java,
                    "yasma")
                .build()
        }
    }
    return DATABASE_INSTANCE
}



