package com.talview.yasma.samsruti.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.databinding.ListItemPostBinding
import com.talview.yasma.samsruti.domain.Post

class PostsListAdapter(val clickListener: CallBackClickListener): ListAdapter<Post, PostsListAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var viewDataBinding: ListItemPostBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){

        fun bind(post: Post){
            viewDataBinding.eachPost = post
            updateUserNameTextView(post)
            viewDataBinding.executePendingBindings()
        }

        private fun updateUserNameTextView(post: Post) {
            viewDataBinding.postedByUser.text = viewDataBinding.root.context.getString(R.string.display_user_name,post.userId)
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
        return PostViewHolder(ListItemPostBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
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




