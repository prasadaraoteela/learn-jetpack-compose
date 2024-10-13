package me.prasad.jetpack.compose.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import me.prasad.jetpack.compose.data.model.Answer
import me.prasad.jetpack.compose.ui.viewmodel.SectionViewModel

@Preview(showBackground = true)
@Composable
fun MoneyPlan(
  modifier: Modifier = Modifier,
  sectionViewModel: SectionViewModel = viewModel()
) {

  val state by sectionViewModel.uiState.collectAsState()

  LazyColumn(modifier = modifier) {
    items(state.sections) { section ->
      SurveyAnswer(answer = Answer(title = section.title))
    }
  }
}