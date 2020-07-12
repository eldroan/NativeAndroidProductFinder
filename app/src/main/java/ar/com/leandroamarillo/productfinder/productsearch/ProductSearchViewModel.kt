package ar.com.leandroamarillo.productfinder.productsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ar.com.leandroamarillo.productfinder.network.MeliApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ProductSearchViewModel : ViewModel() {
    val search = MutableLiveData<String>()
    val canSearch: LiveData<Boolean> = Transformations.map(search) { value -> value.isNotEmpty() }
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response
    private val _busy = MutableLiveData<Boolean>()
    val busy: LiveData<Boolean> = _busy


    init {
        Timber.i("Inicializando ProductSearchViewModel")
        search.value = ""
        _response.value = ""
        _busy.value = false
    }

    fun search() {
        if (canSearch.value == true) {
            Timber.i("Buscando con valor %s", search.value)
            _busy.value = true
            MeliApi
                .meliApiService
                .getSearch(search.value ?: "")
                .enqueue(object: Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        val message = "Failure: " + t.message
                        Timber.e("Fallo la busqueda con error %s",message)
                        _response.value = message
                        _busy.value = false
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        _response.value = response.body()
                        _busy.value = false
                    }
                })

        }
    }
}