package com.example.vinilosapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp.Utilities.hideKeyboard
import com.example.vinilosapp.application.VinilosUniandesApplication
import com.example.vinilosapp.data.model.Album
import com.example.vinilosapp.databinding.FragmentAlbumSearchBinding
import com.example.vinilosapp.presentation.vm.AlbumDetailsViewModel
import com.example.vinilosapp.presentation.vm.AlbumSearchViewModel
import com.example.vinilosapp.ui.adapter.AlbumSearchAdapter


/**
 * Fragment to show Album list data
 * **/
class AlbumSearchFragment: Fragment() {

    private lateinit var viewModelAlbum: AlbumSearchViewModel
    private lateinit var adapterAlbum : AlbumSearchAdapter
    private lateinit var searchList : RecyclerView
    private lateinit var emptyText : TextView
    private lateinit var searchTextField : EditText
    private lateinit var albumDetailsVM: AlbumDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get the view model
        viewModelAlbum = ViewModelProvider(requireActivity(), VinilosUniandesApplication.provideViewModelFactory(requireContext()))
            .get(AlbumSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlbumSearchBinding.inflate(inflater, container, false)

        initScreenItems(binding)
        initAdapter()
        initSearch()
        updateAlbumSearchListFromInputEmpty()
        return binding.root
    }

    private fun initScreenItems(binding: FragmentAlbumSearchBinding){
        // Initialise screen views
        searchList = binding.list
        emptyText = binding.emptyList
        searchTextField = binding.searchRepo
    }


    private fun initAdapter() {
        // Initialise search adapter for  recycle view
        adapterAlbum = AlbumSearchAdapter {
            navigate(it)
        }

        // Assign adapter to recycler view
        searchList.adapter = adapterAlbum

        // Subscribe to data changes in view model to show Album search results
        viewModelAlbum.albums.observe(viewLifecycleOwner, Observer<List<Album>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapterAlbum.setDataSet(it)
        })

        // Subscribe to data changes in view model to show network errors
        viewModelAlbum.networkErrors.observe(viewLifecycleOwner, Observer<String> {
            Log.d("Search", it)
            if (it.isNotEmpty()){
                Toast.makeText(requireContext(), "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
            }

        })
    }


    private fun initSearch() {
        // Trigger search from search button in keyboard.
        searchTextField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateAlbumSearchListFromInput()
                true
            } else {
                false
            }
        }

        // Trigger search from enter button on keyboard.
        searchTextField.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateAlbumSearchListFromInput()
                true
            } else {
                false
            }
        }
    }


    private fun updateAlbumSearchListFromInput() {
        // Hide keyboard after search is triggered
        hideKeyboard()

        // Search for album with album name presented in search field
        searchTextField.text.trim().let {
            if (it.isNotEmpty()) {
                searchList.scrollToPosition(0)
                viewModelAlbum.searchAlbums(it.toString())
                adapterAlbum.setDataSet(emptyList())
            }
        }
    }

    private fun updateAlbumSearchListFromInputEmpty() {
        // Hide keyboard after search is triggered
        //hideKeyboard()

        // Search for album with album name presented in search field

                searchList.scrollToPosition(0)
                viewModelAlbum.searchAlbums("")
                adapterAlbum.setDataSet(emptyList())


    }


    private fun showEmptyList(show: Boolean) {
        // show or hid list based on size of list data returned
        if (show) {
            emptyText.visibility = View.VISIBLE
            searchList.visibility = View.GONE
        } else {
            emptyText.visibility = View.GONE
            searchList.visibility = View.VISIBLE
        }
    }


    // Navigate to Album details
    private fun navigate(album: Album){
        albumDetailsVM = ViewModelProvider(requireActivity(), VinilosUniandesApplication.provideViewModelFactory(requireActivity())).get(AlbumDetailsViewModel::class.java)
        albumDetailsVM.setSelectedAlbum(album)

        val action = AlbumSearchFragmentDirections.actionSearchFragmentToDetailsFragment()
        findNavController().navigate(action)
    }
}