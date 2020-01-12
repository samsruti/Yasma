package com.talview.yasma.samsruti.repository

import com.talview.yasma.samsruti.base.BaseMockServerTest
import com.talview.yasma.samsruti.di.AppComponent
import com.talview.yasma.samsruti.diTest.MockedDIComponent
import org.koin.core.context.startKoin

abstract class BaseRepositoryMockTest : BaseMockServerTest() {
    override fun setUp() {
        super.setUp()
        startKoin {
            val mockedDIComponet = MockedDIComponent()
            modules(
                listOf(
                    AppComponent().viewModelModule,
                    mockedDIComponet.getRetrofitComponent(mockServer.url("/").toString()),
                    mockedDIComponet.getRepositoryComponent(daoMocked)
                )
            )
        }
    }
}