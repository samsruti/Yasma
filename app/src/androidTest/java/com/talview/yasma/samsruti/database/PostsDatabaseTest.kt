package com.talview.yasma.samsruti.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.talview.yasma.samsruti.utils.shared.getLiveDataValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsDatabaseTest : YasmaDatabaseTest(){

    val dbPostA = DatabasePost(id = 1, userId = 1, title = "Title1", body = "Body1")
    val dbPostB = DatabasePost(id = 2, userId = 1, title = "Title2", body = "Body2")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val databasePostList = mutableListOf(dbPostA,dbPostB)

    @Before
    fun initDb(){
        runBlocking {
            yasmaDao.insertAllPost(*databasePostList.toTypedArray())
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertAllPosts() {
        databasePostList.add(DatabasePost(3,1,"title3", "body3"))
        yasmaDao.insertAllPost(*databasePostList.toTypedArray())
        val allPosts =
            getLiveDataValue(yasmaDao.getPosts())
        assert (!allPosts.isNullOrEmpty())
        Assert.assertEquals(allPosts.size, 3)
    }

    @Test
    @Throws(Exception::class)
    fun getAllPosts() {
        val allPosts =
            getLiveDataValue(yasmaDao.getPosts())
        Assert.assertEquals(allPosts.size, 2)

    }
}