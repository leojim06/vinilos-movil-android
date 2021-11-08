package com.example.vinilosapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilosapp.R
import com.example.vinilosapp.data.model.Album
import com.example.vinilosapp.databinding.AlbumSearchItemBinding

/**
 * View holder to display recycler view list data
 * **/
class AlbumViewHolder(binding: AlbumSearchItemBinding, val navigateToDetail: (Album) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    private val albumNameTxt = binding.albumTitleTxt
    private val artistNameTxt = binding.artistNameTxt
    private val albumArtwork = binding.albumThumb


    fun bindData(album: Album){
        albumNameTxt.text = album.name
        artistNameTxt.text = album.genre
        Glide.with(albumArtwork.context).load(album.cover).placeholder(R.drawable.last_fm).into(albumArtwork)

        /**
         * Navigate to details screen
         * **/
        this.itemView.setOnClickListener {
            navigateToDetail(album)
        }
    }


    companion object {
        /**
         * Singleton access to [VideoViewHolder] returning inflated view
         * **/
        fun create(parent: ViewGroup, navigate: (Album) -> Unit): AlbumViewHolder {
            val binding = AlbumSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AlbumViewHolder(binding, navigate)
        }
    }


}