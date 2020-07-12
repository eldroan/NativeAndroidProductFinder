package ar.com.leandroamarillo.productfinder.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.mercadolibre.com/sites/MLA/"
private const val DEFAULT_OFFSET = 0
private const val DEFAULT_LIMIT = 50

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

interface MeliApiService {
    @GET("search")
    fun getSearch(
        @Query("q") query: String,
        @Query("offset") offset: Int = DEFAULT_OFFSET,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): Call<String>
}

object MeliApi {
    val meliApiService: MeliApiService by lazy {
        retrofit.create(MeliApiService::class.java)
    }
}