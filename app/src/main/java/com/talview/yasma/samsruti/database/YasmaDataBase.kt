package com.talview.yasma.samsruti.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabasePost::class, DatabaseAlbums::class, DatabaseComments::class, DatabasePhotos::class],
    version = 1, exportSchema = false)
abstract class YasmaDatabase: RoomDatabase() {
    abstract fun getDao(): YasmaDao

    companion object {
        private const val DB_NAME = "yasmaApp.db"
        @Volatile
        private var INSTANCE: YasmaDatabase? = null

        fun getInstance(context: Context): YasmaDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createdDB(context).also { INSTANCE = it }
            }

        private fun createdDB(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                YasmaDatabase::class.java,
                DB_NAME
            ).build()

    }
}