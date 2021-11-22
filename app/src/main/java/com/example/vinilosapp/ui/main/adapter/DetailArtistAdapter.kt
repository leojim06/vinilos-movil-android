package com.example.vinilosapp.ui.main.adapter


import android.annotation.SuppressLint
import android.widget.TableRow
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.vinilosapp.data.model.ArtistResponse
import com.example.vinilosapp.databinding.ActivityDetailArtistBinding
import java.text.DateFormat
import java.util.*

class DetailArtistAdapter(private val artistDetail: ArtistResponse, private val isBand: Boolean) {

    @SuppressLint("SetTextI18n")
    fun adaptData(binding: ActivityDetailArtistBinding) {
        Glide.with(binding.imageViewArtist.context)
            .load(artistDetail.image)
            .into(binding.imageViewArtist)
        binding.textContentArtist.text = "${artistDetail.name} (${textBandArtist(binding)}) "
        binding.textContentDate.text =
            if (isBand) formatDate(artistDetail.creationDate) else formatDate(artistDetail.birthDate)
        binding.textContentDescription.text = artistDetail.description

    }

    private fun textBandArtist(binding: ActivityDetailArtistBinding):String{
       var resp = ""
        resp = if (isBand) "Banda" else "Musico"
        return resp
    }

    private fun formatDate(date: Date?): String {
        return DateFormat.getDateInstance(DateFormat.LONG).format(date).toString()
    }


    private fun adaptStringAbums(binding: ActivityDetailArtistBinding): String {
            val sb = StringBuilder()
            for (i in artistDetail.albums) {
                sb.append( "- ${i.name} (${DateFormat.getDateInstance(DateFormat.MEDIUM).format(i.releaseDate)})" + "\n")
            }
            return sb.toString()
        }



}
