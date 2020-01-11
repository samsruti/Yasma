package com.talview.yasma.samsruti.ui.albums.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.talview.yasma.samsruti.database.getDatabase
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.repository.AlbumPhotosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailsViewModel(
    currentAlbum: Album,
    app: Application
) : AndroidViewModel(app) {

    private val viewModelJob = Job()
    private val uiCoroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val database = getDatabase(app)
    private val albumPhotosRepository = AlbumPhotosRepository(database)

    private val _selectedAlbum = MutableLiveData<Album>()

    private val _allPhotos = MutableLiveData<List<Photo>>()
    val allPhotos: LiveData<List<Photo>>
        get() = _allPhotos

    init {
        _selectedAlbum.value = currentAlbum

        _status.value = ApiStatus.LOADING
        uiCoroutineScope.launch {
            fetchAllPhotos(albumPhotosRepository.getAllPhotos(currentAlbum.id))
        }
    }

    fun fetchAllPhotos(allPhotos: List<Photo>?){
        if (allPhotos == null){
            _status.value = ApiStatus.ERROR
        } else if(allPhotos.isEmpty()){
            _status.value = ApiStatus.UNSUCCESSFUL
        } else {
            _allPhotos.value = allPhotos
            _status.value = ApiStatus.DONE
        }
    }

    internal class Factory(
        private val currentAlbum: Album,
        private val application: Application
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailsViewModel::class.java)){
                return AlbumDetailsViewModel(currentAlbum, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
