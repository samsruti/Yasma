package com.talview.yasma.samsruti.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.talview.yasma.samsruti.R

class AlbumsFragment : Fragment() {

    private lateinit var albumsViewModel: AlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        albumsViewModel =
            ViewModelProviders.of(this).get(AlbumsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_albums, container, false)
        val textView: TextView = root.findViewById(R.id.text_albums)
        albumsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}