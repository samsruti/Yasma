package com.talview.yasma.samsruti.ui.albums.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.talview.yasma.samsruti.databinding.FragmentAlbumDetailsBinding
import com.talview.yasma.samsruti.domain.ApiStatus
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailsFragment : Fragment() {


    private val viewModel  by viewModel<AlbumDetailsViewModel>()


    val albumDetailsArgs: AlbumDetailsFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getKoin().setProperty("selectedAlbum",albumDetailsArgs.selectedAlbum)

        val binding = FragmentAlbumDetailsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.selectedAlbumViewModel = viewModel
        binding.albumPhotosRecyclerView.adapter = AlbumPhotosGridAdapter(AlbumPhotosGridAdapter.CallBackClickListener{
            Toast.makeText(activity, "Photo: $it", Toast.LENGTH_SHORT).show()
        })

        viewModel.status.observe(this, Observer {
            if (it == ApiStatus.UNKNOWN_HOST) {
                val snackbar: Snackbar= Snackbar.make(binding.root, "No Internet Connection", Snackbar.LENGTH_LONG)

            }
        })


        return binding.root
    }


}
