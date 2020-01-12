package com.talview.yasma.samsruti.di

import com.google.gson.GsonBuilder
import com.talview.yasma.samsruti.database.YasmaDatabase
import com.talview.yasma.samsruti.network.YasmaApiService
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.ui.albums.AlbumsViewModel
import com.talview.yasma.samsruti.ui.albums.detail.AlbumDetailsViewModel
import com.talview.yasma.samsruti.ui.posts.PostsViewModel
import com.talview.yasma.samsruti.ui.posts.detail.PostDetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val CONNECT_TIMEOUT = 10L
private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class AppComponent {

    fun getComponentModuleList(): List<Module> {
        return listOf(networkModule, repositoryModule, roomModule, viewModelModule)
    }

    val networkModule = module {

        single { GsonBuilder().create() }

        single {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            OkHttpClient.Builder().apply {
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
                addInterceptor(httpLoggingInterceptor)
            }.build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
        }

        factory { get<Retrofit>().create(YasmaApiService::class.java) }
    }

    val repositoryModule = module {
        factory { YasmaRepository(get(), get()) }
    }

    val roomModule = module {
        single { YasmaDatabase.getInstance(get()) }
        single { get<YasmaDatabase>().getDao() }
    }

    val viewModelModule = module {
        viewModel { PostsViewModel(get()) }
        viewModel { AlbumsViewModel(get()) }
        viewModel { AlbumDetailsViewModel(get(),getProperty("selectedAlbum")) }
        viewModel { PostDetailsViewModel(get(),getProperty("selectedPost")) }
    }

}