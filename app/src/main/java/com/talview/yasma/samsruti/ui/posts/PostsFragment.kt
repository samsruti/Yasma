package com.talview.yasma.samsruti.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.databinding.FragmentPostsBinding
import com.talview.yasma.samsruti.domain.ApiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private val postsViewModel by viewModel<PostsViewModel>()

    private var postListsAdapter: PostsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentPostsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = postsViewModel
        postListsAdapter = PostsListAdapter(PostsListAdapter.CallBackClickListener{
            postsViewModel.displayPostDetails(it)

        })


        binding.postRecyclerView.apply {
            adapter = postListsAdapter
        }

        postsViewModel.status.observe(this, Observer {
            when(it){
                ApiStatus.UNKNOWN_HOST -> {
                    val snackbar: Snackbar = Snackbar
                        .make(activity!!.findViewById(R.id.nav_host_fragment),"No Internet Connection",Snackbar.LENGTH_LONG)
                    snackbar.show()
//                    TODO: set Anchor view for the snackbar
                }

                ApiStatus.LOADING ->
                    binding.shimmerLayout.startShimmer()
                else ->

                    binding.shimmerLayout.stopShimmer()
            }
        })

        postsViewModel.navigateToSelectedPost.observe(this, Observer {currentPost ->
            if (currentPost != null) {
                findNavController()
                    .navigate(PostsFragmentDirections
                                .actionNavigationPostsListsToPostDetailsFragment(currentPost.id, currentPost))
                postsViewModel.displayPostDetailsComplete()
            }
        })

        return binding.root
    }
}