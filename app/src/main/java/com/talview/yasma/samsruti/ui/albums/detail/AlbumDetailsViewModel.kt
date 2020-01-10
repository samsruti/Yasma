package com.talview.yasma.samsruti.ui.albums.detail


import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Photo

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailsViewModel(
    currentAlbum: Album,
    app: Application
) : AndroidViewModel(app) {

    private val _selectedAlbum = MutableLiveData<Album>()
    val selectedAlbum: LiveData<Album>
        get() = _selectedAlbum

    private val _allPhotos = MutableLiveData<List<Photo>>()
    val allPhotos: LiveData<List<Photo>>
        get() = _allPhotos

    init {
        _selectedAlbum.value = currentAlbum
        val listPhotos = mutableListOf<Photo>(
            Photo(1,1,"https://via.placeholder.com/150/92c952","Title1","https://via.placeholder.com/600/92c952"),
            Photo(1,2,"https://via.placeholder.com/150/771796","Title2","https://via.placeholder.com/600/771796"),
            Photo(1,3,"https://via.placeholder.com/150/24f355","Title3","https://via.placeholder.com/600/24f355"),
            Photo(1,4,"https://via.placeholder.com/150/24f355","Title3","https://via.placeholder.com/600/24f355")
        )
        _allPhotos.value = listPhotos
    }
}
