package com.example.vinilosapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [AlbumDetailsResults] data class to hold album details data
 * **/
data class AlbumDetailsResults (
    val album: AlbumDetails
)

@Entity(tableName = "album_details")
data class AlbumDetails (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String?,
    val cover: String?,
    val releaseDate: String?,
    val description: String?,
    val genre: String?,
    val recordLabel: String?,
    @Embedded
    val tracks: Tracks?
)

data class Tracks (
    val track: List<Track>
)

data class Track (
    val name: String?,
    val url: String?,
    val duration: String?
)

data class Wiki (
    val published: String?,
    val summary: String?,
    val content: String?
)