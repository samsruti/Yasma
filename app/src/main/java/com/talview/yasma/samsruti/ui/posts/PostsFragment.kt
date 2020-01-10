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
import com.talview.yasma.samsruti.domain.Post

class PostsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this).get(PostsViewModel::class.java)
    }

    private var postListsAdapter: PostsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPostsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.viewModel = postsViewModel

        postListsAdapter = PostsListAdapter(PostsListAdapter.CallBackClickListener{
            postsViewModel.displayPostDetails(it)
        })

        binding.postRecyclerView.apply {
            adapter = postListsAdapter
        }

        postsViewModel.navigateToSelectedPost.observe(this, Observer {currentPost ->
            if (currentPost != null) {
                findNavController()
                    .navigate(PostsFragmentDirections
                                .actionNavigationPostsListsToPostDetailsFragment(currentPost.id, currentPost))
                postsViewModel.displayPostDetailsComplete()
            }
        })

        postsViewModel.allPosts.observe(viewLifecycleOwner, Observer {posts ->
            postListsAdapter?.submitList(posts)

        })

        return binding.root
    }
}