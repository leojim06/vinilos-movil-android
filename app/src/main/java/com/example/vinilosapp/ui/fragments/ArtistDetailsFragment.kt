package com.example.vinilosapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp.application.VinilosUniandesApplication
import com.example.vinilosapp.data.model.ArtistDetails
import com.example.vinilosapp.databinding.FragmentArtistDetailsBinding
import com.example.vinilosapp.presentation.vm.ArtistDetailsViewModel

class ArtistDetailsFragment : Fragment() {
    private var _binding: FragmentArtistDetailsBinding? = null
    private val binding get() = _binding!!


    lateinit var viewModel : ArtistDetailsViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArtistDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity(), VinilosUniandesApplication.provideViewModelFactory(requireActivity()))
            .get(ArtistDetailsViewModel::class.java)
        binding.vm = viewModel

        initPage(binding)
        subscribeToDataChanges()
        return binding.root
    }

    private fun initPage(binding: FragmentArtistDetailsBinding){
        binding.loadingProgress
        binding.artistTxt
    }

    private fun showDetails() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showArtistsDetails(artistDetails: ArtistDetails){

    }


    private fun subscribeToDataChanges(){
        viewModel.getArtistDetails()
        viewModel.artistsDetails.observe(viewLifecycleOwner, Observer {
            showDetails()
            it?.let {
                showArtistsDetails(it)
                viewModel.artistsDetails.value = null
            }
        })
        viewModel.networkErrors.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), "it", Toast.LENGTH_SHORT).show()
            }
        })
    }


}