package com.example.vinilosapp.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.vinilosapp.data.model.AlbumResponse
import com.example.vinilosapp.databinding.ItemLayoutBinding
import com.example.vinilosapp.ui.main.view.DetailAlbumActivity

const val ID = "id"
const val NAME = "name"


class MainAdapter(
    private val albums: ArrayList<AlbumResponse>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    lateinit var context: Context


    class DataViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val bindPar = binding
        fun bind(album: AlbumResponse) {
            bindPar.root.apply {
                bindPar.textViewAlbumName.text = album.name
                bindPar.textAlbumRecord.text = album.recordLabel
                Glide.with(bindPar.imageViewAvatar.context)
                    .load(album.cover)
                    .into(bindPar.imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        context = parent.context
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bindPar.root.setOnClickListener {
            //Log.d("hola"," mundo")
            val intent = Intent(context, DetailAlbumActivity::class.java).apply {
                putExtra(ID, albums[position].id.toString())
                putExtra(NAME, albums[position].name)
            }

            context.startActivity(intent)

        }

        holder.bind(albums[position])
    }

    fun addAlbums(albums: List<AlbumResponse>) {
        this.albums.apply {
            clear()
            addAll(albums)
        }

    }
}