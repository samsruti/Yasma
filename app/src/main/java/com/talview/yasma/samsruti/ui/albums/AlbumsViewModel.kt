package com.talview.yasma.samsruti.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class AlbumsViewModel(albumRepository: YasmaRepository) : BaseViewModel() {

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


    val allAlbums = albumRepository.albums

    init {
        _status.value = ApiStatus.LOADING
        mainScope.launch {

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

}