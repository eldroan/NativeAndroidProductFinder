package ar.com.leandroamarillo.productfinder

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.leandroamarillo.productfinder.model.Product
import ar.com.leandroamarillo.productfinder.productlist.ProductAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Product>?){
    val adapter = recyclerView.adapter  as ProductAdapter
    adapter.submitList(data)
}