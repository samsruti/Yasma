package com.talview.yasma.samsruti.di

import com.talview.yasma.samsruti.repository.YasmaRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { YasmaRepository(get(), get()) }
}