package com.lovys.assignment.ui.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.annotation.NonNull

import androidx.recyclerview.widget.RecyclerView


import com.lovys.assignment.databinding.ListItemBinding
import com.lovys.assignment.domain.localdb.Beer

const val TYPE_HEADER = 0
const val TYPE_ITEM = 1

class PunkAdapter(private val beerList: List<Beer>) :
    RecyclerView.Adapter<PunkAdapter.PunkViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(item: Beer)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PunkViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)

        return PunkViewHolder(
            ListItemBinding.inflate(inflater, viewGroup, false)
        )
    }

    override fun onBindViewHolder(
        @NonNull holder: PunkViewHolder, i: Int
    ) {
        //setting the data on the UI field
        val currentItem = beerList[i]
        holder.binding.beerImage.setImageURI(currentItem.imageURL)
        holder.binding.beerName.text = currentItem.name
        holder.binding.beerTagline.text = currentItem.tagline
        holder.binding.beerAbv.text = currentItem.abv.toString()


        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(beerList[i])
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) TYPE_HEADER else TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    override fun getItemCount() = beerList.size + 1

    class PunkViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}