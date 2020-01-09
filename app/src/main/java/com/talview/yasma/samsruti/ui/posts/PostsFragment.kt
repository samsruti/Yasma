package com.talview.yasma.samsruti.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.talview.yasma.samsruti.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this).get(PostsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPostsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.viewModel = postsViewModel

        binding.postRecyclerView.adapter = PostsListAdapter(PostsListAdapter.CallBackClickListener{
            postsViewModel.displayPostDetails(it)
        })

        postsViewModel.navigateToSelectedPost.observe(this, Observer {currentPost ->
            if (currentPost != null) {
                findNavController()
                    .navigate(PostsFragmentDirections
                                .actionNavigationPostsListsToPostDetailsFragment(currentPost.id))
                postsViewModel.displayPostDetailsComplete()
            }
        })



//        textView.setOnClickListener {
//            )
//        }

        return binding.root
    }
}