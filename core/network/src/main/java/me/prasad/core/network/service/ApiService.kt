package me.prasad.core.network.service

import me.prasad.core.network.model.NetworkPhoto
import me.prasad.core.network.model.Product
import retrofit2.http.GET

interface ApiService {

  @GET("/photos")
  suspend fun fetchPhotos(): List<NetworkPhoto>

  @GET("/vegetables")
  suspend fun fetchVegetables(): List<Product>
}