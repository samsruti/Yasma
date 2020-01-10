package com.talview.yasma.samsruti.ui.posts.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talview.yasma.samsruti.databinding.ListItemPostCommentBinding
import com.talview.yasma.samsruti.domain.Comment

class PostCommentsListAdapter(val clickListener: CallBackClickListener)
    : ListAdapter<Comment, PostCommentsListAdapter.PostCommentViewHolder>(DiffCallback) {

    class PostCommentViewHolder(private var viewDataBinding: ListItemPostCommentBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){

        fun bind(comment: Comment){
            viewDataBinding.eachComment = comment
            viewDataBinding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentViewHolder {
        return PostCommentViewHolder(ListItemPostCommentBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: PostCommentViewHolder, position: Int) {
        val currentComment = getItem(position)
        Log.v("Comment", "$currentComment")
        holder.itemView.setOnClickListener {
            clickListener.clickListener(currentComment)
        }
        holder.bind(currentComment)

    }

    class CallBackClickListener(val clickListener: (comment: Comment) -> Unit) {
        fun onClick(comment: Comment) = clickListener(comment)
    }
}




