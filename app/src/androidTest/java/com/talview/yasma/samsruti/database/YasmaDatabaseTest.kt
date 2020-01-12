package com.talview.yasma.samsruti.database

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

abstract class YasmaDatabaseTest {

    protected lateinit var yasmaDao: YasmaDao
    protected lateinit var db: YasmaDatabase

    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, YasmaDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        yasmaDao = db.getDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }



}

