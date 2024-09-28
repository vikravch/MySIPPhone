package com.vikravch.sampleapp.simple_feature.data.repository

import com.vikravch.sampleapp.core.NetworkInfo
import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class QuoteNetworkKtorRepository(
    private val httpClient: HttpClient,
    private val networkInfo: NetworkInfo
): QuoteRepository {
    override suspend fun getQuote(): Result<String> {
        return try {
            val response = httpClient.get("https://api.kanye.rest/")
            Result.success(response.bodyAsText())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}