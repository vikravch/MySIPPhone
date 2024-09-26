package com.vikravch.sampleapp.simple_feature.data.remote.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
    @GET("/")
    suspend fun getQuote(): Response<QuoteDTO>
}