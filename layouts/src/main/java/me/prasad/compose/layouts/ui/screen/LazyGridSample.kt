package me.prasad.compose.layouts.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import me.prasad.compose.layouts.R

@Composable
fun LazyGridSample(modifier: Modifier = Modifier) {
  LazyVerticalGrid(
    modifier = modifier.padding(8.dp),
    columns = object : GridCells {
      override fun Density.calculateCrossAxisCellSizes(
        availableSize: Int,
        spacing: Int
      ): List<Int> {

        val firstColumn = (availableSize - spacing) * 2 / 3
        val secondColumn = availableSize - spacing - firstColumn

        return listOf(firstColumn, secondColumn)
      }
    },
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    contentPadding = PaddingValues(8.dp)
  ) {
    items(10) {
      LazyGridSampleItem()
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LazyGridSamplePreview() {
  LazyGridSample()
}

@Composable
fun LazyGridSampleItem(modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(bottomStart = 16.dp),
    color = MaterialTheme.colorScheme.secondaryContainer
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painterResource(R.drawable.ic_launcher_background), contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .height(160.dp),
        contentScale = ContentScale.Crop
      )
      Text(
        "Tomato",
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(16.dp)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LazyGridSampleItemPreview() {
  LazyGridSampleItem()
}