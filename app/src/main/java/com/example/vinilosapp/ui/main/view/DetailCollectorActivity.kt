package com.example.vinilosapp.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.api.RetrofitBuilder
import com.example.vinilosapp.data.model.CollectorResponse
import com.example.vinilosapp.databinding.ActivityDetailCollectorBinding
import com.example.vinilosapp.ui.base.ViewModelFactory
import com.example.vinilosapp.ui.main.adapter.DetailCollectorAdapter
import com.example.vinilosapp.ui.main.viewmodel.CollectorViewModel
import com.example.vinilosapp.utils.Status

const val ID = "id"

class DetailCollectorActivity : AppCompatActivity() {

    private lateinit var collectorViewModel: CollectorViewModel
    private lateinit var adapter: DetailCollectorAdapter

    private lateinit var binding: ActivityDetailCollectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCollectorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getStringExtra(ID)!!

        setupViewModel()
        setupObservers(id)
    }

    private fun setupViewModel() {
        collectorViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[CollectorViewModel::class.java]
    }

    private fun setupObservers(id: String) {
        collectorViewModel.getCollectorDetail(id).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { collectorDetail -> retrieveCollectorDetail(collectorDetail) }
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

    private fun retrieveCollectorDetail(collector: CollectorResponse) {
        adapter = DetailCollectorAdapter(collector)
        adapter.adaptData(binding)
        supportActionBar?.title = collector.name
        supportActionBar?.subtitle = "Coleccionista"
    }
}