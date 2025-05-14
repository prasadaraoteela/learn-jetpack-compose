package me.prasad.compose.layouts.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.compose.layouts.ui.theme.AppTheme2

@Composable
fun TextStylingSample(modifier: Modifier = Modifier) {

  var showMore by remember { mutableStateOf(false) }
  var maxLines by remember { mutableIntStateOf(8) }
  var textFieldValue by remember { mutableStateOf("") }

  val gradientColors = listOf(Color.Green, Color.Yellow, Color.Red)

  Surface(
    modifier = modifier.padding(16.dp),
    color = MaterialTheme.colorScheme.surfaceVariant,
    shape = RoundedCornerShape(
      topEnd = 16.dp,
      bottomStart = 16.dp,
      bottomEnd = 16.dp
    )
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      BasicTextField(
        value = textFieldValue,
        onValueChange = {
          textFieldValue = it
        },
        cursorBrush = Brush.linearGradient(gradientColors),
        decorationBox = { innerTextField ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color.Cyan)
              .padding(16.dp)
          ) {
            Icon(Icons.Default.DateRange, contentDescription = null)
            innerTextField()
          }
        }
      )
      TextField(
        value = textFieldValue,
        onValueChange = {
          textFieldValue = it
        },
        label = {
          Text("Message #composers")
        },
        colors = TextFieldDefaults.colors(
          focusedIndicatorColor = Color.Green,
          unfocusedIndicatorColor = Color.Yellow
        )
      )
      Text(
        text = buildAnnotatedString {
          append("@google.com Take a look at the ")
          val spanStyle = SpanStyle(
            fontFamily = FontFamily.Monospace,
            background = Color.White
          )
          withStyle(style = spanStyle) {
            append("Flow.collectAsState()")
          }

          append(" APIs")
        },
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = {
          if (it.hasVisualOverflow) {
            showMore = true
          }
        }
      )

      if (showMore) {
        Button(onClick = {
          maxLines = 16
          showMore = false
        }) {
          Text("More")
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun TextStylingSamplePreview() {
  AppTheme2 {
    TextStylingSample()
  }
}