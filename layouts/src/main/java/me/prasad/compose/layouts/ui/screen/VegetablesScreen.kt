package me.prasad.compose.layouts.ui.screen

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
import me.prasad.compose.layouts.business.Vegetable
import me.prasad.compose.layouts.ui.viewmodel.VegetableViewModel


@Composable
fun VegetablesScreen(
  modifier: Modifier = Modifier,
  vegetableViewModel: VegetableViewModel = viewModel()
) {
  val vegetableUiState by vegetableViewModel.uiState.collectAsState()
  VegetablesList(modifier = modifier, vegetables = vegetableUiState.vegetables)
}

@Composable
fun VegetablesList(
  modifier: Modifier = Modifier,
  vegetables: List<Vegetable>
) {

  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    contentPadding = PaddingValues(8.dp)
  ) {
    items(vegetables) { vegetable ->
      VegetableItem(vegetable = vegetable)
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun VegetablesListPreview() {
  VegetablesList(
    vegetables = listOf(
      Vegetable(
        id = 1,
        name = "Cabbage",
        photoUrl = "https://www.freepngimg.com/thumb/categories/2970.png",
        quantity = "",
        price = 12.00
      )
    )
  )
}

@Composable
fun VegetableItem(
  modifier: Modifier = Modifier,
  vegetable: Vegetable
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(16.dp),
    color = MaterialTheme.colorScheme.surface
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      AsyncImage(
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
          .background(Color.White),
        model = vegetable.photoUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
      )
      Text(
        text = vegetable.name,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleSmall
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun VegetableItemPreview() {
  VegetableItem(
    vegetable = Vegetable(
      name = "Cabbage",
      photoUrl = "https://www.freepngimg.com/thumb/categories/2970.png",
      quantity = "One Unit",
      id = 2,
      price = 30.00
    )
  )
}