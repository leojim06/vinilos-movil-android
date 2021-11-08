package com.example.vinilosapp.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilosapp.data.database.VinilosUniandesDatabase
import com.example.vinilosapp.data.database.factory.AlbumFactory
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * [CachedAlbumDaoTest] class to test Data access object
 * **/
@RunWith(AndroidJUnit4::class)
class CachedAlbumDaoTest {

    private lateinit var dataBase: VinilosUniandesDatabase

    @Before
    fun initDb() {
        dataBase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), VinilosUniandesDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        dataBase.close()
    }


    @Test
    fun `check_album_list_saves`() {
        val cachedAlbum = AlbumFactory().makeAlbum()
        dataBase.albumsDao().insertAll(listOf(cachedAlbum))

        val albums = dataBase.albumsDao().resultsByQuery(cachedAlbum.name.toString())
        albums.value?.isNotEmpty()?.let { assert(it) }
    }


    @Test
    fun `check_album_Details_saves`(){
        val cachedAlbumDetail = AlbumFactory().makeAblumDtails()
        dataBase.albumsDao().insertAlbumDetails(cachedAlbumDetail)

        val albumDetails = dataBase.albumsDao().albumByMid(cachedAlbumDetail.id!!)
        assertThat(albumDetails.name, equalTo("Poeta del Pueblo"))
    }


    @Test
    fun `check_deleting_Album`(){
        val cachedAlbum = AlbumFactory().makeAlbum()
        dataBase.albumsDao().insertAll(listOf(cachedAlbum))

        dataBase.albumsDao().clearAlbums(cachedAlbum.name!!)
        dataBase.albumsDao().resultsByQuery(cachedAlbum.name!!).value?.isEmpty()?.let { assert(it) }
    }

}