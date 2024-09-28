package com.vikravch.sampleapp.core

import androidx.annotation.Keep
import com.google.gson.Gson
import retrofit2.HttpException

@Keep
class UserNotFoundException : Exception("User not found")
@Keep
class NoInternetException : Exception("No internet connection")
@Keep
class ServerException(message: String) : Exception(message)
@Keep
class WrongRequestException(message: String) : Exception(message)

@Keep
data class ServerError(
    val code: Int,
    val message: String
)

@Keep
fun HttpException.toServerError(): ServerError {
    val gson = Gson()
    return gson.fromJson(this.response()?.errorBody()?.charStream(), ServerError::class.java)
}