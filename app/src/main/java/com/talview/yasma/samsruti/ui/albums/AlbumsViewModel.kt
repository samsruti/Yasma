package com.talview.yasma.samsruti.ui.albums

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talview.yasma.samsruti.database.getDatabase
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.repository.AlbumRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlbumsViewModel(app: Application) : ViewModel() {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val database = getDatabase(app)

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

    private val albumRepository = AlbumRepository(database)

    val allAlbums = albumRepository.albums

    init {
        _status.value = ApiStatus.LOADING
        uiCoroutineScope.launch {

            fetchAllAlbums(albumRepository.getAllAlbums())
        }
    }



    fun fetchAllAlbums(albums: List<Album>?) {
        if (albums == null){
            _status.value = ApiStatus.ERROR
        } else if(albums.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _status.value = ApiStatus.DONE
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}