package com.talview.yasma.samsruti.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.databinding.FragmentAlbumsBinding
import com.talview.yasma.samsruti.databinding.FragmentPostsBinding
import com.talview.yasma.samsruti.ui.posts.PostsFragmentDirections
import com.talview.yasma.samsruti.ui.posts.PostsListAdapter

class AlbumsFragment : Fragment() {

    private val albumsViewModel: AlbumsViewModel by lazy {
        ViewModelProviders.of(this).get(AlbumsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAlbumsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.viewModel = albumsViewModel

        binding.albumsRecyclerView.adapter = AlbumListAdapter(AlbumListAdapter.CallBackClickListener{
            albumsViewModel.displayAlbumDetails(it)
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