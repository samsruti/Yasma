package com.talview.yasma.samsruti.di

import com.talview.yasma.samsruti.database.YasmaDatabase
import org.koin.dsl.module

val roomModule = module {
    single { YasmaDatabase.getInstance(get()) }
    single { get<YasmaDatabase>().getDao() }
}