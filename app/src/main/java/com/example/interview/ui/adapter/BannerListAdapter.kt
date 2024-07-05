package com.example.interview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.interview.data.BannerContent
import com.example.interview.databinding.ItemListBinding

class BannerListAdapter : RecyclerView.Adapter<BannerListAdapter.ViewHolder>() {

    private var filteredItems: ArrayList<BannerContent> = ArrayList()
    val getFilteredItems: ArrayList<BannerContent>
        get() = filteredItems
    private var originalItems: ArrayList<BannerContent> = ArrayList()

    fun updateData(newList: List<BannerContent>){
        filteredItems.clear()
        originalItems.clear()
        filteredItems.addAll(newList)
        originalItems.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BannerContent){
            binding.tvBodyContent.text = data.title
            binding.tvTitleContent.text = data.body
            binding.imageHolder.setImageDrawable(ContextCompat.getDrawable(binding.root.context,data.img))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(filteredItems[position])
    }

    override fun getItemCount(): Int = filteredItems.size

    fun filter(query: String) {
        filteredItems = if (query.isEmpty()) {
            ArrayList(originalItems)
        } else {
            ArrayList(originalItems.filter { it.body.contains(query, ignoreCase = true) })
        }
        notifyDataSetChanged()
    }
}

