package com.talview.yasma.samsruti.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.talview.yasma.samsruti.R
import com.talview.yasma.samsruti.domain.*
import com.talview.yasma.samsruti.ui.albums.AlbumListAdapter
import com.talview.yasma.samsruti.ui.albums.detail.AlbumPhotosGridAdapter
import com.talview.yasma.samsruti.ui.posts.PostsListAdapter
import com.talview.yasma.samsruti.ui.posts.detail.PostCommentsListAdapter
import timber.log.Timber

@BindingAdapter("postListData")
fun bindAllPostRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {

    val adapter = recyclerView.adapter as PostsListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("albumListData")
fun bindAllAlbumsRecyclerView(recyclerView: RecyclerView, data: List<Album>?) {
    val adapter = recyclerView.adapter as AlbumListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("postCommentListData")
fun bindAllCommentsRecyclerView(recyclerView: RecyclerView, data: List<Comment>?) {
    val adapter = recyclerView.adapter as PostCommentsListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("albumPhotosGridData")
fun bindAlbumPhotosRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    val adapter = recyclerView.adapter as AlbumPhotosGridAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
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
        //Todo: Resizing animation
            .into(imageView)
    }
}

@BindingAdapter("showWhenListEmpty")
fun <T> View.showOnlyWhenListEmpty(data: List<T>? ){
//    Timber.d("Binding: $data")
    this.visibility = when {
        data.isNullOrEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}

fun View.setViewVisibility(status: ApiStatus){
    when (status) {
        ApiStatus.LOADING-> {
            visibility = View.VISIBLE
        }
        ApiStatus.ERROR -> {
           visibility = View.VISIBLE
        }
        ApiStatus.DONE -> {
            visibility = View.GONE
        }
        ApiStatus.UNSUCCESSFUL -> {
            visibility = View.GONE
        }

    }
}

@BindingAdapter("showTextForEmptyRecyclerView")
fun showTextForEmptyRecyclerView(textView: TextView, status: ApiStatus){
//    Timber.d("Binding: $data")
    textView.setViewVisibility(status)

    if (status == ApiStatus.LOADING) {
        textView.text = "Loading"
    }
    else if (status == ApiStatus.ERROR) {
        textView.text = "Error!"

    } else if (status == ApiStatus.UNSUCCESSFUL){
        textView.text = "Empty"
    }
}

@BindingAdapter("showImageForEmptyRecyclerView")
fun showImageForEmptyRecyclerView(imageView: ImageView, status: ApiStatus){
//    Timber.d("Binding: $data")

    imageView.setViewVisibility(status)

    if (status == ApiStatus.LOADING) {
        imageView.setImageResource(R.drawable.photo_loading_animation)
    }
    else if (status == ApiStatus.ERROR) {
        imageView.setImageResource(R.drawable.ic_error_outline_black_24dp)
    }
}