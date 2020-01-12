package com.talview.yasma.samsruti.diTest


import com.google.gson.GsonBuilder
import com.talview.yasma.samsruti.database.YasmaDao
import com.talview.yasma.samsruti.network.YasmaApiService
import com.talview.yasma.samsruti.repository.YasmaRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MockedDIComponent{
    fun getRetrofitComponent(mockApi: String) = module {

        single { GsonBuilder().create() }

        single {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            OkHttpClient.Builder().apply {
                retryOnConnectionFailure(true)
                addInterceptor(httpLoggingInterceptor)
            }.build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(mockApi)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
        }

        factory { get<Retrofit>().create<YasmaApiService>() }
    }

    fun getRepositoryComponent(dao: YasmaDao) = module {
        factory { YasmaRepository(get(), dao) }
    }
}

