package ar.com.leandroamarillo.productfinder.productsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ar.com.leandroamarillo.productfinder.network.MeliApi
import ar.com.leandroamarillo.productfinder.network.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ProductSearchViewModel : ViewModel() {
    val search = MutableLiveData<String>()
    val canSearch: LiveData<Boolean> = Transformations.map(search) { value -> value.isNotEmpty() }

    //    private val _response = MutableLiveData<String>()
//    val response: LiveData<String> = _response
    private val _shouldNavigateToList = MutableLiveData<Boolean>()
    val shouldNavigateToList: LiveData<Boolean> = _shouldNavigateToList

    init {
        Timber.i("Inicializando ProductSearchViewModel")
        search.value = ""
        _shouldNavigateToList.value = false
    }

    fun navigationHandled(){
        _shouldNavigateToList.value = false
    }

    fun search() {
        if (canSearch.value == true) {
            _shouldNavigateToList.value = true
//            Timber.i("Buscando con valor %s", search.value)
//            _busy.value = true


        }
    }
}