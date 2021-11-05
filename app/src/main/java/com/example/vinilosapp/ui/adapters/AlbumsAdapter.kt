package com.example.vinilosapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.AlbumItemBinding
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.ui.AlbumFragmentDirections

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    private lateinit var mContext: Context
    private lateinit var stores: MutableList<Album>

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        mContext = parent.context
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
//        val store = stores.get(position)

        holder.viewDataBinding.also {
            it.album = albums[position]
        }

      Glide.with(mContext)
            .load(albums[position].cover)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(holder.viewDataBinding.imgPhoto)

        var isNavigating = false

        holder.viewDataBinding.root.setOnClickListener {
            if (!isNavigating) {
                isNavigating = true
                val action =
                    AlbumFragmentDirections.actionAlbumFragmentToAlbumDetailFragment(albums[position].albumId)
                // Navigate using that action
                holder.viewDataBinding.root.findNavController().navigate(action)
            }
        }


    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }


}