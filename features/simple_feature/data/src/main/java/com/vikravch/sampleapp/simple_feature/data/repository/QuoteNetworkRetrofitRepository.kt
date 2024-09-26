package com.vikravch.sampleapp.simple_feature.data.repository

import com.vikravch.sampleapp.simple_feature.data.remote.retrofit.QuoteApi
import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository

class QuoteNetworkRetrofitRepository(
    private val quoteApi: QuoteApi
): QuoteRepository {
    override suspend fun getQuote(): Result<String> {
        val response = quoteApi.getQuote()
        if (!response.isSuccessful) {
            return Result.failure(Exception("Failed to get quote"))
        }
        return Result.success(response.body()?.quote ?: "This is a quote")
    }
}