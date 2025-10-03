package com.arambyeol.ui.state

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: UiError) : UiState<Nothing>()
    data object Empty : UiState<Nothing>()
}