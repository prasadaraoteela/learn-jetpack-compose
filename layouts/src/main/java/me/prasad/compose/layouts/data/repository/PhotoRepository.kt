package me.prasad.compose.layouts.data.repository

import me.prasad.compose.layouts.business.Photo

interface PhotoRepository {

  suspend fun getPhotos(): List<Photo>
}