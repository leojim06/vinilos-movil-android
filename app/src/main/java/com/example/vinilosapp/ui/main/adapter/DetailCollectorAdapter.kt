package com.example.vinilosapp.ui.main.adapter

import com.example.vinilosapp.data.model.CollectorResponse
import com.example.vinilosapp.databinding.ActivityDetailCollectorBinding

class DetailCollectorAdapter(private val collectorDetail: CollectorResponse) {
    fun adaptData(binding: ActivityDetailCollectorBinding) {
        binding.textCollectorNameValue.text = collectorDetail.name
        binding.textCollectorPhoneValue.text = collectorDetail.telephone
        binding.textCollectorEmailValue.text = collectorDetail.email
    }
}