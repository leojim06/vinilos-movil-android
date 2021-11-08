package com.example.vinilosapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vinilosapp.data.model.Album
import com.example.vinilosapp.data.model.AlbumDetails

@Database(entities = [Album::class, AlbumDetails::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class VinilosUniandesDatabase: RoomDatabase() {

    /** [VideosDao] instance  **/
    abstract fun albumsDao(): AlbumsDao

    /**
     * Database singleton access
     * **/
    companion object{

        @Volatile
        private var INSTANCE: VinilosUniandesDatabase? = null

        fun getInstance(context : Context): VinilosUniandesDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                VinilosUniandesDatabase::class.java, "VinilosUniandes.db")
                .build()
    }
}