package me.prasad.compose.layouts.data.repository

import me.prasad.compose.layouts.business.Photo
import me.prasad.core.network.service.ApiService
import javax.inject.Inject

class DefaultPhotoRepository @Inject constructor(
  private val api: ApiService
) : PhotoRepository {
  override suspend fun getPhotos(): List<Photo> =
    api.fetchPhotos().map { photo ->
      Photo(
        albumId = photo.albumId,
        id = photo.id,
        title = photo.title,
        url = photo.url,
        thumbnailUrl = photo.thumbnailUrl
      )
    }
}