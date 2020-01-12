package com.talview.yasma.samsruti.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

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
        mainScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                fetchAllAlbums(albumRepository.getAllAlbums())
            } catch (e:Exception){
                Timber.d("Error: $e")
                _status.value = ApiStatus.UNKNOWN_HOST
            }
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