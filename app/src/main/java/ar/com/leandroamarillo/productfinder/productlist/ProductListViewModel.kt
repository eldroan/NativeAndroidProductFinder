package ar.com.leandroamarillo.productfinder.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ar.com.leandroamarillo.productfinder.model.Product
import ar.com.leandroamarillo.productfinder.network.MeliApi
import ar.com.leandroamarillo.productfinder.network.model.Results
import ar.com.leandroamarillo.productfinder.network.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

const val LIMIT = 50 // Any more than this errors out the api

class ProductListViewModel(
    private val search: String
) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products
    private val _busy = MutableLiveData<Boolean>()
    val busy: LiveData<Boolean> = _busy
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    val errorVisible: LiveData<Boolean> =
        Transformations.map(_error) { value -> value.isNotEmpty() }
    private val _offset = MutableLiveData<Int>()
    private lateinit var call: Call<SearchResponse>

    init {
        _offset.value = 0
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

                _products.value = products.map { p ->
                    Product(
                        p.id,
                        p.siteId,
                        p.title,
                        p.price ?: 0.0,
                        p.thumbnail,
                        p.acceptsMercadopago
                    )
                }
                Timber.i("SUCCESS: %s products retrieved", products.size.toString())
                _error.value = ""
                _busy.value = false
            }
        })
    }

    //TODO: Handle "Error": "The specified resource is not available at the moment." when querying with large offsets
    fun searchNextPage() {
        //TODO: next page should be added at the end, currently replaces existing results
        _offset.value = _offset.value?.plus(LIMIT)
        Timber.i("Retrieving page %s", _offset.value)
        searchProducts()
    }

    override fun onCleared() {
        super.onCleared()
        call.cancel()
    }
}