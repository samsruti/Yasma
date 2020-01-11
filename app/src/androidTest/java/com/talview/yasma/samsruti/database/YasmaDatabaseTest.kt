package com.talview.yasma.samsruti.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.talview.yasma.samsruti.domain.Post
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class YasmaDatabaseTest {

    private lateinit var yasmaDao: YasmaDao
    private lateinit var db: YasmaDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, YasmaDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        yasmaDao = db.yasmaDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAllPosts() {
        val posts = mutableListOf<Post>(Post(id = 1, userId = 1, title = "Title", body = "Body"))
        val databaseModel = posts.map {
            DatabasePost(id = it.id, userId = it.userId, title = it.title, body = it.body)
        }.toTypedArray()

        yasmaDao.insertAllPost(*databaseModel)
        val allPosts = yasmaDao.getPosts()
       assert (!allPosts.value.isNullOrEmpty())
    }
}

