package com.example.vinilosapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.ActivityMainBinding.inflate
import com.example.vinilosapp.databinding.AlbumDetailBinding
import com.example.vinilosapp.databinding.AlbumDetailBinding.inflate
import com.example.vinilosapp.databinding.AlbumFragmentBinding
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.ui.adapters.AlbumsAdapter
import com.example.vinilosapp.viewmodels.AlbumDetailViewModel
import com.example.vinilosapp.viewmodels.AlbumViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumDetailViewModel
    private var viewModelAdapter: AlbumsAdapter? = null
    private lateinit var mGridLayout: GridLayoutManager
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    private fun setupViewModel() {
        viewModel.comments.observe(viewLifecycleOwner, {
            setUiStore(it)
        })

    }

    private fun setUiStore(storeEntity: List<Album>) {
        with(_binding){
            this?.txtName?.text = storeEntity[0].name
            this?.txtDescription?.text = storeEntity[0].description
            loadImage(storeEntity[0].cover)
            this?.txtFecha?.text = "Fecha Lanzamiento: " + storeEntity[0].releaseDate
//            this?.txtGenero?.text = storeEntity[0].genre
        }
    }


    private fun loadImage(url: String){
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(_binding?.imgAlbum!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        val args: AlbumDetailFragmentArgs by navArgs()
        Log.d("Args", args.albumId.toString())
        viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory(activity.application, args.albumId)).get(AlbumDetailViewModel::class.java)
        viewModel.comments.observe(viewLifecycleOwner, Observer<List<Album>> {
            it.apply {
                //viewModelAdapter!!.albums = this
                //_binding.albumsRv.
                setUiStore(it)
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}