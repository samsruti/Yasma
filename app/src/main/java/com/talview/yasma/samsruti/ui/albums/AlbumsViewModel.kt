package com.talview.yasma.samsruti.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.ApiStatus

class AlbumsViewModel : ViewModel() {

    private val _allAlbums = MutableLiveData<List<Album>>()
    val allAlbums: LiveData<List<Album>>
        get() = _allAlbums

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _navigateToSelectedAlbum = MutableLiveData<Album>()
    val navigateToSelectedAlbum: LiveData<Album>
        get() = _navigateToSelectedAlbum

    fun displayAlbumDetails(album: Album) {
        _navigateToSelectedAlbum.value = album
    }

    fun displayAlbumDetailsComplete() {
        _navigateToSelectedAlbum.value = null
    }

    init {

        val list = mutableListOf<Album>(
            Album(1,1,"album1"),
            Album(1,2,"album2"),
            Album(1,3,"album3")
        )
        _allAlbums.value = list
    }
}