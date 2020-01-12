package com.talview.yasma.samsruti.di

import com.talview.yasma.samsruti.ui.albums.AlbumsViewModel
import com.talview.yasma.samsruti.ui.albums.detail.AlbumDetailsViewModel
import com.talview.yasma.samsruti.ui.posts.PostsViewModel
import com.talview.yasma.samsruti.ui.posts.detail.PostDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PostsViewModel(get()) }
    viewModel { AlbumsViewModel(get()) }
    viewModel { AlbumDetailsViewModel(get(),getProperty("selectedAlbum")) }
    viewModel { PostDetailsViewModel(get(),getProperty("selectedPost")) }
}
