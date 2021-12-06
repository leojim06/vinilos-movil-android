package com.example.vinilosapp.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.api.RetrofitBuilder
import com.example.vinilosapp.data.model.AlbumResponse
import com.example.vinilosapp.databinding.ActivityAlbumCreateBinding
import com.example.vinilosapp.ui.base.ViewModelFactory
import com.example.vinilosapp.ui.main.adapter.ID
import com.example.vinilosapp.ui.main.viewmodel.CreateAlbumViewModel
import com.example.vinilosapp.utils.Status
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumCreateActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumCreateActivity : AppCompatActivity() {
    private lateinit var createAlbumViewModel: CreateAlbumViewModel
    private lateinit var binding: ActivityAlbumCreateBinding
    private var formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getStringExtra(ID)!!

        setupViewModel()

        binding.albumCreate.setOnClickListener {
            if (binding.albumName.text.isEmpty() || binding.albumCover.text.isEmpty() || binding.albumReleaseDate.text.isEmpty() || binding.albumGenre.selectedItem.toString()
                    .equals("-- Género --") || binding.albumRecord.text.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Por favor, ingrese todos los campos obligatorios para crear el álbum.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                postAlbum()
            }
        }
    }

    private fun postAlbum() {
        val album = AlbumResponse()
        album.name = binding.albumName.text.toString()
        album.cover = binding.albumCover.text.toString()
        album.releaseDate = formatter.parse(binding.albumReleaseDate.text.toString())
        album.description = binding.albumDescription.text.toString()
        album.genre = binding.albumGenre.selectedItem.toString()
        album.recordLabel = binding.albumRecord.text.toString()
        createAlbumViewModel.createAlbum(album).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                            Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun setupViewModel() {
        createAlbumViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[CreateAlbumViewModel::class.java]
    }

}