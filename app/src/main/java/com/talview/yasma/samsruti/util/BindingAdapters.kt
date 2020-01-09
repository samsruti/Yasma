package com.talview.yasma.samsruti.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.ui.posts.PostsListAdapter

@BindingAdapter("postListData")
fun bindAllPostRecyclerView(recyclerView: RecyclerView, data: List<Post>) {
    val adapter = recyclerView.adapter as PostsListAdapter
    adapter.submitList(data)
}