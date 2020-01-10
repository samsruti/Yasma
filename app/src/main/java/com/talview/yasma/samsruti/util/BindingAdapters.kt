package com.talview.yasma.samsruti.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.ui.albums.AlbumListAdapter
import com.talview.yasma.samsruti.ui.posts.PostsListAdapter
import com.talview.yasma.samsruti.ui.posts.detail.PostCommentsListAdapter

@BindingAdapter("postListData")
fun bindAllPostRecyclerView(recyclerView: RecyclerView, data: List<Post>) {
    val adapter = recyclerView.adapter as PostsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("albumListData")
fun bindAllAlbumsRecyclerView(recyclerView: RecyclerView, data: List<Album>) {
    val adapter = recyclerView.adapter as AlbumListAdapter
    adapter.submitList(data)
}

@BindingAdapter("postCommentListData")
fun bindAllCommentsRecyclerView(recyclerView: RecyclerView, data: List<Comment>) {
    val adapter = recyclerView.adapter as PostCommentsListAdapter
    adapter.submitList(data)
}