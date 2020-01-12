package com.talview.yasma.samsruti.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.talview.yasma.samsruti.databinding.FragmentAlbumsBinding
import com.talview.yasma.samsruti.domain.ApiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : Fragment() {

    private val albumsViewModel by viewModel<AlbumsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAlbumsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        binding.viewModel = albumsViewModel

//        binding.albumsRecyclerView.layoutParams =

        binding.albumsRecyclerView.adapter = AlbumListAdapter(AlbumListAdapter.CallBackClickListener{
            albumsViewModel.displayAlbumDetails(it)
        })



        albumsViewModel.status.observe(this, Observer {
            when(it){
                ApiStatus.LOADING ->
                    binding.shimmerLayout.startShimmer()
                else ->
                    binding.shimmerLayout.stopShimmer()
            }
        })


        albumsViewModel.navigateToSelectedAlbum.observe(this, Observer {currentAlbum ->
            if (currentAlbum != null) {
                findNavController()
                    .navigate(
                        AlbumsFragmentDirections
                            .actionNavigationAlbumListsToAlbumDetailsFragment(currentAlbum.id, currentAlbum))
                albumsViewModel.displayAlbumDetailsComplete()
            }
        })

        return binding.root
    }
}