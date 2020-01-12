package com.talview.yasma.samsruti.ui.posts.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.talview.yasma.samsruti.databinding.FragmentPostDetailsBinding
import com.talview.yasma.samsruti.domain.ApiStatus
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PostDetailsFragment : Fragment() {

    private val postDetailsFragmentArgs: PostDetailsFragmentArgs by navArgs()

//    private val viewModel: PostDetailsViewModel by lazy {
//        val application = requireNotNull(activity).application
//
//        val currentPost = postDetailsFragmentArgs.selectedPost
//        val viewModelFactory = PostDetailsViewModel.Factory(currentPost, application)
//        ViewModelProviders.of(this, viewModelFactory).get(PostDetailsViewModel::class.java)
//    }


    private val viewModel by viewModel<PostDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        getKoin().setProperty("selectedPost", postDetailsFragmentArgs.selectedPost)

        val binding = FragmentPostDetailsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it){
                ApiStatus.UNKNOWN_HOST -> {
                    Snackbar.make(binding.root,"No Internet Connection", Snackbar.LENGTH_LONG).show()
                }

                ApiStatus.LOADING ->
                    binding.shimmerLayout.startShimmer()
                else ->

                    binding.shimmerLayout.stopShimmer()
            }
        })

        binding.dropDownIcon.setOnClickListener {

            when (binding.commentsRecyclerView.visibility){
                View.GONE -> {
                    binding.commentsRecyclerView.visibility = View.VISIBLE
                }

                View.VISIBLE ->{
                    binding.commentsRecyclerView.visibility = View.GONE
                }

            }
            binding.dropDownIcon.animate().rotationBy(180F).setDuration(300).start()
        }



        binding.commentsRecyclerView.adapter = PostCommentsListAdapter(PostCommentsListAdapter.CallBackClickListener{
            Toast.makeText(activity, "Clicked: $it",Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

}
