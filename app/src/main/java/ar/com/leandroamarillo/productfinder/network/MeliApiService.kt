package ar.com.leandroamarillo.productfinder.network

import ar.com.leandroamarillo.productfinder.network.model.SearchResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.mercadolibre.com/sites/MLA/"
private const val DEFAULT_OFFSET = 0
private const val DEFAULT_LIMIT = 50

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface MeliApiService {
    @GET("search")
    fun getSearch(
        @Query("q") query: String,
        @Query("offset") offset: Int = DEFAULT_OFFSET,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): Call<SearchResponse>
}

object MeliApi {
    val meliApiService: MeliApiService by lazy {
        retrofit.create(MeliApiService::class.java)
    }
}