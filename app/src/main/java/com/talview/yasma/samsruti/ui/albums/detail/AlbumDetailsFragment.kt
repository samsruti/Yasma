package com.talview.yasma.samsruti.ui.albums.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.talview.yasma.samsruti.databinding.FragmentAlbumDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailsFragment : Fragment() {

    val albumDetailsArgs: AlbumDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application

        val currentAlbum = albumDetailsArgs.selectedAlbum

        val viewModelFactory = AlbumDetailsViewModelFactory(currentAlbum, application)

        val binding = FragmentAlbumDetailsBinding.inflate(inflater,container,false)
        binding.setLifecycleOwner(this)
        binding.selectedAlbumViewModel =  ViewModelProviders.of(this, viewModelFactory)
            .get(AlbumDetailsViewModel::class.java)

        binding.albumPhotosRecyclerView.adapter = AlbumPhotosGridAdapter(AlbumPhotosGridAdapter.CallBackClickListener{
            Toast.makeText(activity, "Photo: $it", Toast.LENGTH_SHORT).show()
        })


        return binding.root
    }


}
