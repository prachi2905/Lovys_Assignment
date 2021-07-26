package com.lovys.assignment.domain.utils

sealed class Results<out T : Any> {
    class Success<out T : Any>(val data: T?) : Results<T>()
    class Failure(val exception: Exception) : Results<Nothing>()
}
