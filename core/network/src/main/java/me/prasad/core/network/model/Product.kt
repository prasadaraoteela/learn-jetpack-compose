package me.prasad.core.network.model

import com.google.gson.annotations.SerializedName

data class Product(
  @SerializedName("id")
  val id: Long,
  @SerializedName("name")
  val name: String,
  @SerializedName("photo_url")
  val photoUrl: String,
  @SerializedName("quantity")
  val quantity: String,
  @SerializedName("price")
  val price: Double
)
