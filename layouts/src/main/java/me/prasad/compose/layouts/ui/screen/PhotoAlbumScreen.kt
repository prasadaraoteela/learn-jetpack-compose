package me.prasad.compose.layouts.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import me.prasad.compose.layouts.R
import me.prasad.compose.layouts.business.Photo
import me.prasad.compose.layouts.ui.viewmodel.PhotoViewModel


@Composable
fun PhotoAlbumScreen(
  modifier: Modifier = Modifier,
  photoAlbumViewModel: PhotoViewModel = viewModel()
) {
  val photoAlbumUiState by photoAlbumViewModel.uiState.collectAsState()
  PhotoAlbum(modifier = modifier, photos = photoAlbumUiState.photos)
}

@Composable
fun PhotoAlbum(
  modifier: Modifier = Modifier,
  photos: List<Photo>
) {

  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    contentPadding = PaddingValues(8.dp)
  ) {
    items(photos, key = { photo -> photo.id }) { photo ->
      PhotoAlbumTile(photo = photo)
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PhotoAlbumPreview() {
  PhotoAlbum(
    photos = listOf(
      Photo(
        albumId = 1,
        id = 1,
        title = "accusamus beatae ad facilis cum similique qui sunt",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
      )
    )
  )
}

@Composable
fun PhotoAlbumTile(
  modifier: Modifier = Modifier,
  photo: Photo
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(16.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f)
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      AsyncImage(
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
          .background(Color.White),
        model = photo.thumbnailUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
      )
      Text(
        text = photo.title,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleSmall,
        maxLines = 2
      )
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PhotoAlbumTilePreview() {
  PhotoAlbumTile(
    photo = Photo(
      albumId = 1,
      id = 1,
      title = "accusamus beatae ad facilis cum similique qui sunt",
      url = "https://via.placeholder.com/600/92c952",
      thumbnailUrl = "https://via.placeholder.com/150/92c952"
    )
  )
}