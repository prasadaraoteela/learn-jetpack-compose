package me.prasad.compose.layouts.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.compose.layouts.R
import me.prasad.compose.layouts.ui.theme.AppTheme2
import java.time.LocalTime

@Composable
fun AnimationSample(modifier: Modifier = Modifier) {
  var showMore by remember { mutableStateOf(false) }

  Surface(
    modifier = modifier.padding(16.dp),
    color = MaterialTheme.colorScheme.surfaceVariant,
    shape = RoundedCornerShape(
      topEnd = 16.dp,
      bottomStart = 16.dp,
      bottomEnd = 16.dp
    )
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Text(
        modifier = Modifier.clickable {
          showMore = !showMore
        },
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
        overflow = TextOverflow.Ellipsis,
        onTextLayout = {
          if (it.hasVisualOverflow) {
            showMore = true
          }
        }
      )

      ExpandingText(
        """
        In essence, rememberUpdatedState helps you avoid using stale values in situations where callbacks might be executed after a delay, ensuring that your code behaves as expected.
        I hope this explanation is helpful! Let me know if you have any other questions.
      """.trimIndent()
      )

      AnimatedVisibility(
        showMore,
        modifier = Modifier.align(Alignment.End)
      ) {
        Text(
          LocalTime.now().toString(),
          style = MaterialTheme.typography.labelLarge,
          modifier = Modifier.padding(16.dp)
        )
      }
    }
  }
}

@Composable
fun ExpandingText(description: String) {
  var expanded by remember { mutableStateOf(false) }

  Text(
    description,
    modifier = Modifier
      .animateContentSize(
        animationSpec = spring(
          dampingRatio = Spring.DampingRatioMediumBouncy,
          stiffness = Spring.StiffnessLow
        )
      )
      .clickable {
        expanded = !expanded
      }, maxLines = if (!expanded) 2 else 10
  )
}

@Preview(showBackground = true)
@Composable
fun ImageBorderAnimation() {
  val rainbowColorBrush = Brush.sweepGradient(
    listOf(
      Color.Red,
      Color.Yellow,
      Color.Green,
      Color.Blue,
    )
  )
  val infiniteTransition = rememberInfiniteTransition(label = "ImageBorderAnimation")
  val rotationAnimation = infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)),
    label = "ImageBorderAnimation"
  )
  Image(
    painterResource(R.drawable.ic_launcher_background),
    contentDescription = null,
    modifier = Modifier
      .drawBehind {
        rotate(rotationAnimation.value) {
          drawCircle(rainbowColorBrush, style = Stroke(width = 10f))
        }
      }
      .padding(10.dp)
      .clip(CircleShape)
  )
}

@Preview(showBackground = true)
@Composable
private fun AnimationSamplePreview() {
  AppTheme2 {
    AnimationSample()
  }
}