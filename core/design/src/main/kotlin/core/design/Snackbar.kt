package core.design

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Snackbar @Inject constructor() {

  data class Message(
    val message: String,
    val actionLabel: String? = null,
    val withDismissAction: Boolean = false,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val action: (() -> Unit)? = null
  )

  private val _messages = MutableSharedFlow<Message>()
  val messages: SharedFlow<Message> = _messages

  @Composable
  fun register(): SnackbarHostState {

    val state = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
      messages.collect { message ->
        scope.launch {
          val result = state.showSnackbar(
            message = message.message,
            actionLabel = message.actionLabel,
            withDismissAction = message.withDismissAction,
            duration = message.duration
          )

          if (result == SnackbarResult.ActionPerformed) {
            message.action?.invoke()
          }
        }
      }
    }

    return state
  }

  suspend fun showSnackbar(snackbarState: Message) {
    _messages.emit(snackbarState)
  }

  suspend fun showSnackbar(
    message: String,
    actionLabel: String? = null,
    action: (() -> Unit)? = null
  ) {
    _messages.emit(Message(message, actionLabel, action = action))
  }

  suspend fun showSnackbar(message: String) {
    _messages.emit(Message(message))
  }
}

