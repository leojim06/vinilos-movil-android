package com.example.vinilosapp.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.vinilosapp.R
import com.example.vinilosapp.data.model.CollectorResponse
import com.example.vinilosapp.databinding.ItemLayoutBinding
import com.example.vinilosapp.ui.main.view.DetailCollectorActivity

const val COLLECTOR_ID = "id"

class CollectorAdapter(
    private val collectors: ArrayList<CollectorResponse>
) : RecyclerView.Adapter<CollectorAdapter.DataViewHolder>() {

    lateinit var context: Context

    class DataViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val bindPar = binding
        fun bind(collector: CollectorResponse) {
            bindPar.root.apply {
                bindPar.textViewAlbumName.text = collector.name
                bindPar.textAlbumRecord.text = collector.email
                Glide.with(bindPar.imageViewAvatar.context)
                    .load(R.drawable.ic_collector)
                    .error(R.drawable.ic_collector).placeholder(R.drawable.ic_collector)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(bindPar.imageViewAvatar)
                bindPar.imageViewAvatar.setColorFilter(
                    Color.rgb(162, 0, 0),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        context = parent.context
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = collectors.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bindPar.root.setOnClickListener{
            val intent = Intent(context, DetailCollectorActivity::class.java).apply {
                putExtra(COLLECTOR_ID, collectors[position].id.toString())
            }
            context.startActivity(intent)
        }

        holder.bind(collectors[position])
    }

    fun addCollectors(collectors: List<CollectorResponse>) {
        this.collectors.apply {
            clear()
            addAll(collectors)
        }

    }
}