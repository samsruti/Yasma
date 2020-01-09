package com.talview.yasma.samsruti.ui.posts.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.ui.albums.detail.PostDetailsViewModel

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsFragment : Fragment() {

    private lateinit var postDetailsViewModel: PostDetailsViewModel

    private val postDetailsFragmentArgs: PostDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        postDetailsViewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_post_details, container, false)

//        val textView: TextView = root.findViewById(R.id.text_posts_details)
//        postDetailsViewModel.text.observe(this, Observer {
//            textView.text = "PostID: $it with  ${postDetailsFragmentArgs.postId}"
//        })

        return root
    }

}
