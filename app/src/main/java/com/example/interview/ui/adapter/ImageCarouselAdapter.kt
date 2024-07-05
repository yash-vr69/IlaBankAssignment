package com.example.interview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.interview.databinding.ItemImageCarouselBinding

class ImageCarouselAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<ImageCarouselAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemImageCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(image: Int) {
            binding.imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    image
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(images[position])
    }

    override fun getItemCount(): Int = images.size
}
