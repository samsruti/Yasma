package com.talview.yasma.samsruti.ui.posts

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
import com.talview.yasma.samsruti.ui.albums.AlbumsFragmentDirections

class PostsFragment : Fragment() {

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postsViewModel =
            ViewModelProviders.of(this).get(PostsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_posts, container, false)
//        val textView: TextView = root.findViewById(R.id.text_posts)
//        postsViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
//
//        textView.setOnClickListener {
//            findNavController()
//                .navigate(PostsFragmentDirections.actionNavigationPostsListsToPostDetailsFragment(10))
//        }

        return root
    }
}