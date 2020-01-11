package com.talview.yasma.samsruti.ui.posts.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.databinding.FragmentPostDetailsBinding
import com.talview.yasma.samsruti.databinding.ListItemPostCommentBinding
import com.talview.yasma.samsruti.domain.ApiStatus
import com.talview.yasma.samsruti.ui.albums.detail.PostDetailsViewModel

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsFragment : Fragment() {

    private val postDetailsFragmentArgs: PostDetailsFragmentArgs by navArgs()

    private val viewModel: PostDetailsViewModel by lazy {
        val application = requireNotNull(activity).application

        val currentPost = postDetailsFragmentArgs.selectedPost
        val viewModelFactory = PostDetailsViewModelFactory(currentPost, application)
        ViewModelProviders.of(this, viewModelFactory).get(PostDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        val binding = FragmentPostDetailsBinding.inflate(inflater,container,false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it){
                ApiStatus.LOADING ->
                    binding.shimmerLayout.startShimmer()
                else ->

                    binding.shimmerLayout.stopShimmer()
            }
        })



        binding.commentsRecyclerView.adapter = PostCommentsListAdapter(PostCommentsListAdapter.CallBackClickListener{
            Toast.makeText(activity, "Clicked: $it",Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

}
