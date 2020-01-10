package com.talview.yasma.samsruti.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.domain.Album
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Photo
import com.talview.yasma.samsruti.domain.Post
import com.talview.yasma.samsruti.ui.albums.AlbumListAdapter
import com.talview.yasma.samsruti.ui.albums.detail.AlbumPhotosGridAdapter
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

@BindingAdapter("albumPhotosGridData")
fun bindAlbumPhotosRecyclerView(recyclerView: RecyclerView, data: List<Photo>) {
    val adapter = recyclerView.adapter as AlbumPhotosGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("photoThumbnailURL")
fun bindPhotoThumbnailURL(imageView: ImageView, thumbnailURL: String?) {
    thumbnailURL?.let {
        val imageUri = thumbnailURL.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.photo_loading_animation)
                .error(R.drawable.ic_error_outline_black_24dp)
            )

            .into(imageView)
//        Todo: add error image.
    }

}