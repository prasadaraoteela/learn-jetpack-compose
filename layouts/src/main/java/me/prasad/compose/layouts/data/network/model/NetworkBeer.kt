package me.prasad.compose.layouts.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkBeer(
  @SerializedName("id")
  val id: Int,
  @SerializedName("price")
  val price: String,
  @SerializedName("name")
  val name: String,
  @SerializedName("image")
  val image: String,
  @SerializedName("rating")
  val rating: Rating
)

data class Rating(
  @SerializedName("rate")
  val rate: Double,
  @SerializedName("reviews")
  val reviews: Int
)