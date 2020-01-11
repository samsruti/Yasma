package com.talview.yasma.samsruti.ui.albums.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talview.yasma.samsruti.databinding.GridViewAlbumPhotosItemBinding
import com.talview.yasma.samsruti.domain.Photo

class AlbumPhotosGridAdapter(val clickListener: CallBackClickListener)
    : ListAdapter<Photo, AlbumPhotosGridAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var viewDataBinding: GridViewAlbumPhotosItemBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){

        fun bind(photo: Photo){
            viewDataBinding.currentPhoto = photo
            viewDataBinding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(GridViewAlbumPhotosItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(currentPost)
        }
        holder.bind(currentPost)

    }

    class CallBackClickListener(val clickListener: (photo: Photo) -> Unit) {
        fun onClick(photo: Photo) = clickListener(photo)
    }
}




