package ar.com.leandroamarillo.productfinder.productsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ProductSearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductSearchViewModel::class.java)) {
            return ProductSearchViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}