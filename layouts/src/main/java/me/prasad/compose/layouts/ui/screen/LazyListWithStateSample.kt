package me.prasad.compose.layouts.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class ItemState(val index: Int = 0, val color: Color)

@Composable
fun LazyListWithState(modifier: Modifier = Modifier) {

  val background = MaterialTheme.colorScheme.primary

  val items = remember {
    mutableStateListOf(
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background),
      ItemState(color = background)
    )
  }

  val state = rememberLazyListState()
  val scope = rememberCoroutineScope()

  Box(modifier = modifier.fillMaxSize()) {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      state = state
    ) {
      itemsIndexed(items) { index, item ->
        LazyListItemWithState(item = item,
          onClick = {
            items[index] = items[index].copy(index = index, color = Color.Red)
          })
      }
    }
    FloatingActionButton(
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(16.dp),
      shape = CircleShape,
      onClick = {
        scope.launch {
          state.animateScrollToItem(0)
        }
      },
    ) {
      Icon(Icons.Filled.Add, "Add")
    }
  }
}

@Preview
@Composable
private fun LazyListWithStatePreview() {
  LazyListWithState()
}

@Composable
fun LazyListItemWithState(
  modifier: Modifier = Modifier,
  item: ItemState,
  onClick: () -> Unit = {}
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .height(200.dp)
      .padding(16.dp),
    shape = RoundedCornerShape(20.dp),
    color = item.color,
    onClick = {
      onClick()
    }
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
    ) {
      Text(
        text = "${item.index}", modifier = Modifier
          .padding(16.dp)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun LazyListItemWithStatePreview() {
  LazyListItemWithState(item = ItemState(color = MaterialTheme.colorScheme.background))
}