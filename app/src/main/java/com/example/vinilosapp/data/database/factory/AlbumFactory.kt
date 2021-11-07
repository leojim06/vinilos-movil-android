package com.example.vinilosapp.data.database.factory

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.example.vinilosapp.data.model.Album
import com.example.vinilosapp.data.model.*

/**
 * [AlbumFactory] class to help with testing
 * **/
class AlbumFactory {

    fun makeAlbum() : Album {
        //val image = Image(text = "https://lastfm.freetls.fastly.net/i/u/34s/e17e8ab11291469a84bc869d59b9cf09.png", size = "small")

        return Album(
            idAlbum = 0,
            name = "Poeta del Pueblo",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            releaseDate = "12-12-2021",
            description = "Recopilación de 27 composiciones del cosmos Blades que los bailadores y melómanos han hecho suyas en estos 40 años de presencia de los ritmos y concordias afrocaribeños en múltiples escenarios internacionales. Grabaciones de Blades para la Fania con las orquestas de Pete Rodríguez, Ray Barreto, Fania All Stars y, sobre todo, los grandes éxitos con la Banda de Willie Colón",
            genre = "Salsa",
            recordLabel = "Fania Records"
        )
    }


    fun makeAblumDtails(): AlbumDetails{
        val track = Track(
            name = "balance (mufasa interlude)",
            url = "https://www.last.fm/music/Beyonc%C3%A9/_/balance+(mufasa+interlude)",
            duration = "43"
        )


        val wiki = Wiki(
            published = "19 Jul 2019, 04:42",
            summary = "The Lion King: The Gift is a soundtrack album by Beyoncé, released on July 19, 2019 via Parkwood Entertainment.",
            content = "The Lion King: The Gift is a soundtrack album by Beyoncé, released on July 19, 2019 via Parkwood Entertainment."
        )

        return AlbumDetails(
            idAlbum = 0,
            name = "The Lion King: The Gift",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            releaseDate = "12-12-2021",
            description = "Recopilación de 27 composiciones del cosmos Blades que los bailadores y melómanos han hecho suyas en estos 40 años de presencia de los ritmos y concordias afrocaribeños en múltiples escenarios internacionales. Grabaciones de Blades para la Fania con las orquestas de Pete Rodríguez, Ray Barreto, Fania All Stars y, sobre todo, los grandes éxitos con la Banda de Willie Colón",
            genre = "Salsa",
            recordLabel = "Fania Records"
            //tracks = Tracks(track = listOf(track, track))
        )

    }



}