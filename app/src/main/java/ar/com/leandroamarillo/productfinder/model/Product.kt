package ar.com.leandroamarillo.productfinder.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val acceptsMercadopago: Boolean
) : Parcelable
