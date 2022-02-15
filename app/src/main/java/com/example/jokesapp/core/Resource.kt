package com.example.jokesapp.core

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Failure<T>(message: String) : Resource<T>(message = message)
    class Loading<T>(data: T?=null) : Resource<T>(data)

}