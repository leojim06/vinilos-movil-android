package com.example.vinilosapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [ArtistDetailsResults] data class to hold artis details data
 */
data class ArtistDetailsResults (
    val artist: ArtistDetails
)

@Entity(tableName = "artist")
data class Artist(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

@Entity(tableName = "artist_details")
data class ArtistDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val image: String?,
    var description: String?,
    var birthDate: String?,
    @Embedded
    var albums: Albumes?,
    @Embedded
    var performerPrizes: Prizes?
)

data class Albumes(
    val album: List<Album>
)

data class Prizes(
    val price: List<Price>
)

data class Price(
    val id: Int,
    var premiationDate: String?
)