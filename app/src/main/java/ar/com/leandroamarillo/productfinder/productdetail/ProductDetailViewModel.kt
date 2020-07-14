package ar.com.leandroamarillo.productfinder.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.leandroamarillo.productfinder.model.Product

class ProductDetailViewModel(p: Product) : ViewModel() {
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    init {
        _product.value = p
    }
}