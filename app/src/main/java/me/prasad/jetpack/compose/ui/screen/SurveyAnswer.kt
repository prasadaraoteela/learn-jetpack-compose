package me.prasad.jetpack.compose.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.jetpack.compose.data.model.Answer

@Composable
fun SurveyAnswer(
  answer: Answer,
  modifier: Modifier = Modifier,
  onAnswerSelected: (selectedOption: Answer) -> Unit = {}
) {

  var selectedOption by remember { mutableStateOf(answer.selected) }

  Surface(
    modifier = modifier,
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    shape = MaterialTheme.shapes.small
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Image(imageVector = Icons.Default.Face, contentDescription = null)
      Text(
        text = answer.title,
        modifier = Modifier
          .fillMaxWidth(fraction = 0.9f)
          .padding(8.dp)
      )
      RadioButton(selected = selectedOption, onClick = {
        selectedOption = !selectedOption
        onAnswerSelected(answer)
      })
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun SurveyAnswerPreview() {
  SurveyAnswer(answer = Answer(title = "Spark"))
}
