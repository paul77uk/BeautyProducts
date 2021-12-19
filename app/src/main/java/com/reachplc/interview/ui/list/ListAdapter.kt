package com.reachplc.interview.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.reachplc.interview.model.Product
import com.reachplc.interview.databinding.ListItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val callback = object : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    inner class ListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                productName.text = product.name
                productPrice.text = "£${"%.2f".format(product.price)}"
                Glide.with(imageView.context)
                    .load(product.image)
                    .transform(CenterInside(), RoundedCorners(24))
                    .into(imageView)

                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(product)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

}