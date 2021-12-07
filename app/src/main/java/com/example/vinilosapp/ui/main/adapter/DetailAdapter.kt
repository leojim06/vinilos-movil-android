package com.example.vinilosapp.ui.main.adapter


import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide

import com.example.vinilosapp.data.model.AlbumResponse
import com.example.vinilosapp.databinding.ActivityDetailAlbumBinding
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DetailAdapter(private val albumDetail: AlbumResponse) {


    fun adaptData(binding: ActivityDetailAlbumBinding) {
        Glide.with(binding.imageView2.context)
            .load(albumDetail.cover)
            .into(binding.imageView2)
        binding.textContentDate.text = formatDate(albumDetail.releaseDate)
        binding.textContentGenre.text = albumDetail.genre
        binding.textContentDescription.text = albumDetail.description
    }


    private fun formatDate(date: String?): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        return try {
            formatter.format(parser.parse(date))
        } catch (ex: Exception) {
            println(ex.message)
            return if (date != "" && date != null) {
                date.subSequence(0, 10).toString()
            } else {
                "SIN-FECHA"
            }
        }
    }
}