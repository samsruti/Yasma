package com.talview.yasma.samsruti.ui.albums.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talview.yasma.samsruti.domain.Album
import java.lang.IllegalArgumentException

class AlbumDetailsViewModelFactory(
    private val currentAlbum: Album,
    private val application: Application
): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailsViewModel::class.java)){
                return AlbumDetailsViewModel(currentAlbum, application) as T
            }
            throw IllegalArgumentException("Unknow ViewModel Class")
        }
    }
