package com.talview.yasma.samsruti.base

import com.nhaarman.mockitokotlin2.mock
import com.talview.yasma.samsruti.database.YasmaDao
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.utils.JsonUtils.Companion.getJSON

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

open class BaseMockServerTest : KoinTest {

    protected val yasmaRepository by inject<YasmaRepository>()
    protected var daoMocked = mock<YasmaDao>()

    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    @After
    open fun tearDown() {
        mockServer.shutdown()
        stopKoin()
    }

    fun mockHttpResponse(path: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJSON(path))
    )
}