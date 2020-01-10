package com.talview.yasma.samsruti.ui.albums.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.repository.AlbumPhotosRepository
import com.talview.yasma.samsruti.repository.PostRepository
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


    private val albumPhotosRepository = AlbumPhotosRepository()

    private val _selectedAlbum = MutableLiveData<Album>()
    val selectedAlbum: LiveData<Album>
        get() = _selectedAlbum

    private val _allPhotos = MutableLiveData<List<Photo>>()
    val allPhotos: LiveData<List<Photo>>
        get() = _allPhotos

    init {
        _selectedAlbum.value = currentAlbum
        uiCoroutineScope.launch {
            _allPhotos.value = albumPhotosRepository.getAllPhotos(currentAlbum.id)
        }
    }
}
