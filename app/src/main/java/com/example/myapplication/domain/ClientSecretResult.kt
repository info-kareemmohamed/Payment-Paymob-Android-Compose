package com.example.myapplication.domain

sealed class ClientSecretResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(data: T? = null) : ClientSecretResult<T>(data)
    class Success<T>(data: T?) : ClientSecretResult<T>(data)
    class Error<T>(message: String, data: T? = null) : ClientSecretResult<T>(data, message)
}