package ar.com.leandroamarillo.productfinder

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.leandroamarillo.productfinder.network.model.Results
import ar.com.leandroamarillo.productfinder.productlist.ProductAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Results>?){
    val adapter = recyclerView.adapter  as ProductAdapter
    adapter.submitList(data)
}