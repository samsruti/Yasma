package com.talview.yasma.samsruti.ui.albums.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.ui.posts.detail.PostDetailsFragmentArgs

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailsFragment : Fragment() {

    private lateinit var albumDetailsViewModel: AlbumDetailsViewModel

    val albumDetailsArgs: AlbumDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        albumDetailsViewModel = ViewModelProviders.of(this).get(AlbumDetailsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_album_details, container, false)


        return root
    }


}
