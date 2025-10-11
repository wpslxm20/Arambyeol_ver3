package com.arambyeol.domain.common

sealed class DomainError {
    data object Network : DomainError()
    data object NotFound : DomainError()
    data object Server : DomainError()
    data object Timeout : DomainError()
    data object Unknown : DomainError()
}