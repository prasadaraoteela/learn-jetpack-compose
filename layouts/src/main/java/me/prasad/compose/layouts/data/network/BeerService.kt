package me.prasad.compose.layouts.data.network

import me.prasad.compose.layouts.data.network.model.NetworkBeer
import retrofit2.http.GET

interface BeerService {

  @GET("beers/ale")
  fun fetchBeers(): List<NetworkBeer>
}