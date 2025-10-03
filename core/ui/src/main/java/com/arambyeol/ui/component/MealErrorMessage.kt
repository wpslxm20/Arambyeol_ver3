package com.arambyeol.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.arambyeol.core.ui.R
import com.arambyeol.ui.state.UiError

@Composable
fun MealErrorMessage(error: UiError) {
    val message = when (error) {
        UiError.Network -> stringResource(R.string.error_network)
        UiError.NotFound -> stringResource(R.string.error_not_found)
        UiError.Server -> stringResource(R.string.error_server)
        UiError.Timeout -> stringResource(R.string.error_timeout)
        UiError.Unknown -> stringResource(R.string.error_unknown)
        UiError.BadRequest -> TODO()
    }
    Text(text = message)
}