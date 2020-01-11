package com.talview.yasma.samsruti.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.repository.AlbumRepository
import com.talview.yasma.samsruti.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlbumsViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val albumRepository = AlbumRepository()

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

        uiCoroutineScope.launch {
            _status.value = ApiStatus.LOADING
            fetchAllAlbums(albumRepository.getAllAlbums())
        }
    }

    fun fetchAllAlbums(albums: List<Album>?) {
        if (albums == null){
            _status.value = ApiStatus.ERROR
        } else if(albums.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _allAlbums.value = albums
            _status.value = ApiStatus.DONE
        }
    }
}