package ar.com.leandroamarillo.productfinder.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.leandroamarillo.productfinder.R
import ar.com.leandroamarillo.productfinder.databinding.ProductItemLayoutBinding
import ar.com.leandroamarillo.productfinder.model.Product


class ProductAdapter(private val onClickListener: OnClickListener) : ListAdapter<Product, ProductAdapter.ResultsViewHolder>(DiffCallback) {
    class ResultsViewHolder(private var binding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (product: Product) -> Unit){
        fun onClick(product:Product) = clickListener(product)
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
        val product = getItem(position)
        holder.itemView.findViewById<Button>(R.id.viewProductButton).setOnClickListener {
            it.let{
                onClickListener.onClick(product)
            }
        }
        holder.bind(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ResultsViewHolder(ProductItemLayoutBinding.inflate(layoutInflater))
    }
}