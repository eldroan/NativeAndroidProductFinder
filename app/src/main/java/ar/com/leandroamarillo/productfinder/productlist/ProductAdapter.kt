package ar.com.leandroamarillo.productfinder.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.leandroamarillo.productfinder.databinding.ProductItemLayoutBinding
import ar.com.leandroamarillo.productfinder.model.Product


class ProductAdapter : ListAdapter<Product, ProductAdapter.ResultsViewHolder>(DiffCallback) {
    class ResultsViewHolder(private var binding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ResultsViewHolder(ProductItemLayoutBinding.inflate(layoutInflater))

    }
}