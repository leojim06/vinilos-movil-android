package com.example.vinilosapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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
 * Immutable model class for a lastF album repo that holds all the information about a repository.
 * Objects of this type are received from the lastFm API.
 * This class also defines the Room repos table, where the repo [id] is the primary key which is auto generated
 */
@Entity(tableName = "albums")
data class Album (
    @PrimaryKey(autoGenerate = true)
    val idAlbum: Int = 0,
    val name: String?,
    val cover: String?,
    val releaseDate: String?,
    val description: String?,
    val genre: String?,
    val recordLabel: String?
    //val image: List<Image>?,
    //val mbid: String?
)

