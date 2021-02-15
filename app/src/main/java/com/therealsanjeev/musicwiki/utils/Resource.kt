package com.therealsanjeev.musicwiki.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = "Data is not found!"
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}