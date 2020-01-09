package com.talview.yasma.samsruti.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talview.yasma.samsruti.databinding.ListItemPostBinding
import com.talview.yasma.samsruti.domain.Post

class PostsListAdapter(val clickListener: CallBackClickListener): ListAdapter<Post, PostsListAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var viewDataBinding: ListItemPostBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){

        fun bind(post: Post){
            viewDataBinding.postProperty = post
            viewDataBinding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ListItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(currentPost)
        }
        holder.bind(currentPost)

    }

    class CallBackClickListener(val clickListener: (post: Post) -> Unit) {
        fun onClick(post: Post) = clickListener(post)
    }
}




