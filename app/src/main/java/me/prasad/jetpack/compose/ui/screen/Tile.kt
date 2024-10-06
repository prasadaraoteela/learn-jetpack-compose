package me.prasad.jetpack.compose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.jetpack.compose.data.model.Tile
import me.prasad.jetpack.compose.ui.theme.Typography

@Composable
fun Tile(tile: Tile, modifier: Modifier = Modifier) {

  Card(
    modifier = modifier.padding(8.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White,
    ),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 6.dp
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalArrangement = Arrangement.Start
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = tile.title,
          style = Typography.titleLarge,
        )
        Text(
          text = tile.description,
          style = Typography.bodyMedium,
        )
      }
      Image(
        imageVector = tile.icon, contentDescription = null,
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun TilePreview() {
  Tile(
    tile = Tile(
      title = "Cash Flow View",
      description = "Get a clear picture of your finances with Cash Flow View! Track your income and expenses, identify spending patterns, and make informed financial decisions.",
      icon = Icons.Rounded.Home
    )
  )
}