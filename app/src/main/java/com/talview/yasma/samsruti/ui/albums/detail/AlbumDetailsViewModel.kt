package com.talview.yasma.samsruti.ui.albums.detail


import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.talview.yasma.samsruti.domain.*
import com.talview.yasma.samsruti.repository.YasmaRepository
import com.talview.yasma.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailsViewModel(albumPhotosRepository:YasmaRepository, val currentAlbum: Album) : BaseViewModel(){

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _selectedAlbum = MutableLiveData<Album>()


    val allPhotos = albumPhotosRepository.photos

    init {
        _selectedAlbum.value = currentAlbum


        if (allPhotos.value.isNullOrEmpty()) _status.value = ApiStatus.LOADING

        mainScope.launch {
            try{
                fetchAllPhotos(albumPhotosRepository.getAllPhotos(currentAlbum.id))
            } catch (e:Exception){
                _status.value = ApiStatus.UNKNOWN_HOST
            }

        }
    }

    fun fetchAllPhotos(allPhotos: List<Photo>?){
        if (allPhotos == null){
            _status.value = ApiStatus.ERROR
        } else if(allPhotos.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _status.value = ApiStatus.DONE
        }
    }
}
