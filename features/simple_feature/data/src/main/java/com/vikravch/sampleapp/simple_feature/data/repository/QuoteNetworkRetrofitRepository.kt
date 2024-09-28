package com.vikravch.sampleapp.simple_feature.data.repository

import com.vikravch.sampleapp.core.NetworkInfo
import com.vikravch.sampleapp.core.NoInternetException
import com.vikravch.sampleapp.simple_feature.data.remote.retrofit.QuoteApi
import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository

class QuoteNetworkRetrofitRepository(
    private val quoteApi: QuoteApi,
    private val networkInfo: NetworkInfo
): QuoteRepository {
    override suspend fun getQuote(): Result<String> {
        return if (networkInfo.isConnected()){
            val response = quoteApi.getQuote()
            if (response.isSuccessful) {
                Result.success(response.body()?.quote ?: "This is a quote")
            } else {
                Result.failure(Exception("Failed to get quote"))
            }
        } else {
            Result.failure(NoInternetException())
        }
    }
}