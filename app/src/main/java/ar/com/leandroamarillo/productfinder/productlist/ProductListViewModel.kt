package ar.com.leandroamarillo.productfinder.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ar.com.leandroamarillo.productfinder.model.Product
import ar.com.leandroamarillo.productfinder.network.MeliApi
import ar.com.leandroamarillo.productfinder.network.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

const val LIMIT = 50 // Any more than this errors out the api

class ProductListViewModel(
    private val search: String
) : ViewModel() {
    private val _products = MutableLiveData<MutableList<Product>>()
    val products: LiveData<MutableList<Product>> = _products
    private val _newProducts = MutableLiveData<Boolean>()
    val newProducts: LiveData<Boolean> = _newProducts
    private val _displayNoMoreProducts = MutableLiveData<Boolean>()
    val displayNoMoreProducts: LiveData<Boolean> = _displayNoMoreProducts

    private val _busy = MutableLiveData<Boolean>()
    val busy: LiveData<Boolean> = _busy

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    val errorVisible: LiveData<Boolean> =
        Transformations.map(_error) { value -> value.isNotEmpty() }

    private val _offset = MutableLiveData<Int>()

    private lateinit var call: Call<SearchResponse>
    private val _navigateToProduct = MutableLiveData<Product>()
    val navigateToProduct: LiveData<Product> = _navigateToProduct

    init {
        _offset.value = 0
        _products.value = ArrayList()
        _newProducts.value = false
        _displayNoMoreProducts.value = false
        //TODO: Fix skipping frames on initialization
        searchProducts()
    }

    private fun searchProducts() {
        _busy.value = true
        Timber.i("Starting search with term: %s ", this.search)
        call = MeliApi.meliApiService
            .getSearch(this.search, _offset.value!!, LIMIT)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                val message = "Failure: " + t.message
                Timber.e("Search failed with error: %s", message)
                _error.value = message
                _busy.value = false
            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val products = response.body()?.results ?: listOf()

                if (products.isNotEmpty()) {
                    _products.value!!.addAll(products.map { p ->
                        Product(
                            p.id,
                            p.title,
                            p.price ?: 0.0,
                            p.thumbnail.replace("http://", "https://"),
                            p.acceptsMercadopago
                        )
                    })
                    _newProducts.value = true
                    Timber.i(
                        "RESPONSE: %s products retrieved, now we have %s",
                        products.size.toString(),
                        _products.value!!.size.toString()
                    )

                    // Update offset for next search
                    _offset.value = _offset.value?.plus(LIMIT)
                } else {
                    _displayNoMoreProducts.value = true
                    Timber.i(
                        "There are no more products to see :("
                    )
                }

                _error.value = ""
                _busy.value = false
            }
        })
    }

    //TODO: Handle "Error": "The specified resource is not available at the moment." when querying with large offsets
    fun searchNextPage() {
        Timber.i("Retrieving page %s", _offset.value)
        searchProducts()
    }

    override fun onCleared() {
        super.onCleared()
        call.cancel()
    }

    fun navigateToProduct(product: Product) {
        _navigateToProduct.value = product
    }

    fun navigateToProductHandled() {
        _navigateToProduct.value = null
    }

    fun noMoreProductsHandled() {
        _displayNoMoreProducts.value = false
    }
}