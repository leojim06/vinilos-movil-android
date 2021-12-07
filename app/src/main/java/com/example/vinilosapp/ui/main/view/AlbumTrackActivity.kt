package com.example.vinilosapp.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.vinilosapp.R
import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.api.RetrofitBuilder
import com.example.vinilosapp.databinding.ActivityTrackAlbumBinding
import com.example.vinilosapp.ui.base.ViewModelFactory
import com.example.vinilosapp.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class AlbumTrackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrackAlbumBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

        var numberAlbumId: Number = 0
        val albumId = intent.getStringExtra("idAlbum")
        val nameAlbum = intent.getStringExtra("nameAlbum")
        val postButton: Button = binding.btnCreateTrackAlbum
        if (albumId != null) {
            numberAlbumId = (albumId.toInt())
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Agregar cancion"
        supportActionBar?.subtitle = "Album"

        binding.nameAlbum.text = nameAlbum.toString()

        postButton.setOnClickListener {
            val name = binding.txtName.text.toString()
            val inputMin = binding.textMin.text.toString()
            val inputSeg = binding.textSeg.text.toString()

            val duration = "${inputMin}:${inputSeg}"
            createTrackToAlbum(name, duration, numberAlbumId)
            this.finish()

        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[MainViewModel::class.java]
    }

    private fun createTrackToAlbum(name: String, duration: String, id: Number) {
        lifecycleScope.launch {
            mainViewModel.createTrackToAlbum(name, duration, id)
            val toast =
                Toast.makeText(applicationContext, "Se ha asociado el track", Toast.LENGTH_LONG)
            toast.show()
        }

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

}