package ar.com.leandroamarillo.productfinder.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar.com.leandroamarillo.productfinder.model.Product

@Suppress("UNCHECKED_CAST")
class ProductDetailViewModelFactory (private val product: Product) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            return ProductDetailViewModel(product) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}