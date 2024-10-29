package me.prasad.compose.layouts.data.repository

import me.prasad.compose.layouts.business.Vegetable

interface VegetableRepository {

  suspend fun getVegetables(): List<Vegetable>
}