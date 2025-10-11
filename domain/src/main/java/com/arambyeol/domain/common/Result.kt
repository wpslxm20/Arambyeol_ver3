package com.arambyeol.domain.common

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: DomainError) : Result<Nothing>()
}