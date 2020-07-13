package ar.com.leandroamarillo.productfinder.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar.com.leandroamarillo.productfinder.productsearch.ProductSearchViewModel

@Suppress("UNCHECKED_CAST")
class ProductListViewModelFactory(
    private val search: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(search) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}