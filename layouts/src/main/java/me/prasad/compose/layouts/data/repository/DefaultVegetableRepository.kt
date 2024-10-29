package me.prasad.compose.layouts.data.repository

import me.prasad.compose.layouts.business.Vegetable
import me.prasad.core.network.service.ApiService
import javax.inject.Inject

class DefaultVegetableRepository @Inject constructor(
  private val api: ApiService
) : VegetableRepository {
  override suspend fun getVegetables(): List<Vegetable> =
    api.fetchVegetables().map { product ->
      Vegetable(
        id = product.id,
        name = product.name,
        photoUrl = product.photoUrl,
        quantity = product.quantity,
        price = product.price
      )
    }
}