package com.example.vinilosapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class AlbumResults (
    val results: Results
)

data class Results (
    val albummatches: Albummatches
)

data class Albummatches (
    val album: List<Album>
)

//
data class AlbumDB (
    val album: Album
)

/**
 * Immutable model class for album repo that holds all the information about a repository.
 * Objects of this type are received.
 * This class also defines the Room repos table, where the repo [id] is the primary key which is auto generated
 */
@Entity(tableName = "albums")
data class Album (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val cover: String?,
    val releaseDate: String?,
    val description: String?,
    val genre: String?,
    val recordLabel: String?
    //val image: List<Image>?,
    //val mbid: String?
)

data class Image (
    @field:SerializedName("#text")
    var text: String,
    var size: String
)