package com.example.vinilosapp.ui.main.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.vinilosapp.R
import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.api.RetrofitBuilder
import com.example.vinilosapp.data.model.AlbumModel
import com.example.vinilosapp.databinding.ActivityCreateAlbumBinding
import com.example.vinilosapp.ui.base.ViewModelFactory
import com.example.vinilosapp.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class CreateAlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAlbumBinding
    private lateinit var mainViewModel: MainViewModel

    private var datePickerDialog: DatePickerDialog? = null
    private var selected_date: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Crear Album"
        supportActionBar?.subtitle = "Album"


        val generos = resources.getStringArray(R.array.list_generos)
        val arrayAdapter_genero = ArrayAdapter(this, R.layout.drop_items, generos)
        binding.autoCompleteGeneros.setAdapter(arrayAdapter_genero)

        val nameAlbum = intent.getStringExtra("nameAlbum")
        val postButton: Button = binding.btnCreateTrackAlbum

        val records = resources.getStringArray(R.array.list_records)
        val arrayAdapter_records = ArrayAdapter(this, R.layout.drop_items, records)
        binding.autoCompleteRecords.setAdapter(arrayAdapter_records)

        postButton.setOnClickListener {
            val albumName = binding.txtNameAlbum.text.toString()
            val coverImage = binding.txtUrlCoverAlbum.text.toString()
            val genreText = binding.textInputLayoutGenero.editText!!.text.toString()
            val recordLabel = binding.textInputLayoutRecord.editText!!.text.toString()
            val description = binding.txtDescriptionAlbum.text.toString()
            val dateCreation = binding.txtDateAlbum.text.toString()

            val dateFormatted = SimpleDateFormat("yyyy-MM-dd").format(
                SimpleDateFormat("dd/MM/yyyy").parse(dateCreation)
            )
            val releaseDate = dateFormatted.toString() + "T00:00:00-05:00"
            val album =
                AlbumModel(albumName, coverImage, genreText, recordLabel, description, releaseDate)
            createAlbum(album)
            this.finish()
        }


    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[MainViewModel::class.java]
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    // Override  supportActionBar?.setDisplayHomeAsUpEnabled , close current activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createAlbum(album: AlbumModel) {
        lifecycleScope.launch {
            mainViewModel.createAlbumPost(album)
            val toast =
                Toast.makeText(applicationContext, "Se ha creado el album", Toast.LENGTH_LONG)
            toast.show()
        }

    }

}
