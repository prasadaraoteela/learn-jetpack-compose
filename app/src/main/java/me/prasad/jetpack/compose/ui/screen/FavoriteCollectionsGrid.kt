package me.prasad.jetpack.compose.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.jetpack.compose.R

private val favoriteCollectionData = listOf(
  Pair(R.drawable.fc1_short_mantras, R.string.fc1_short_mantras),
  Pair(R.drawable.fc2_nature_meditations, R.string.fc2_nature_meditations),
  Pair(R.drawable.fc3_stress_and_anxiety, R.string.fc3_stress_and_anxiety),
  Pair(R.drawable.fc4_self_massage, R.string.fc4_self_massage),
  Pair(R.drawable.fc5_overwhelmed, R.string.fc5_overwhelmed),
  Pair(R.drawable.fc6_nightly_wind_down, R.string.fc6_nightly_wind_down)
)

@Composable
fun FavoriteCollectionCard(
  modifier: Modifier = Modifier,
  @DrawableRes drawable: Int,
  @StringRes text: Int
) {
  Surface(
    shape = MaterialTheme.shapes.small,
    modifier = modifier
  ) {
    Row(
      modifier = Modifier.width(192.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Image(
        painterResource(drawable),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(56.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 4.dp,
                    bottomStart = 4.dp
                )
            )
      )
      Text(
        text = stringResource(text),
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.labelMedium
      )
    }
  }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun FavoriteCollectionCardPreview() {
  FavoriteCollectionCard(
    modifier = Modifier.padding(8.dp),
    drawable = R.drawable.fc2_nature_meditations,
    text = R.string.fc2_nature_meditations
  )
}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
  LazyHorizontalGrid(
    modifier = modifier.height(120.dp),
    rows = GridCells.Fixed(2),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(horizontal = 16.dp)
  ) {
    items(favoriteCollectionData) { data ->
      FavoriteCollectionCard(
        drawable = data.first,
        text = data.second
      )
    }
  }
}

@Preview
@Composable
private fun FavoriteCollectionsGridPreview() {
  FavoriteCollectionsGrid()
}
