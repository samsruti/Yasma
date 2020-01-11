package com.talview.yasma.samsruti.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talview.yasma.samsruti.databinding.ListItemAlbumsBinding
import com.talview.yasma.samsruti.domain.Album


class AlbumListAdapter(val clickListener: CallBackClickListener): ListAdapter<Album, AlbumListAdapter.AlbumViewHolder>(DiffCallback) {

    class AlbumViewHolder(private var viewDataBinding: ListItemAlbumsBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){

        fun bind(album: Album){
            viewDataBinding.eachAlbum = album
            viewDataBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AlbumViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemAlbumsBinding.inflate(inflater, parent, false)
                return AlbumViewHolder(binding)
            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder.from(parent)
    }




    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val currentAlbum = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(currentAlbum)
        }
        holder.bind(currentAlbum)

    }

    class CallBackClickListener(val clickListener: (Album: Album) -> Unit) {
        fun onClick(Album: Album) = clickListener(Album)
    }

}




