package com.arambyeol.ui.state

sealed class UiError {
    data object Network : UiError()
    data object NotFound : UiError()
    data object Server : UiError()
    data object BadRequest : UiError()
    data object Timeout : UiError()
    data object Unknown : UiError()
}