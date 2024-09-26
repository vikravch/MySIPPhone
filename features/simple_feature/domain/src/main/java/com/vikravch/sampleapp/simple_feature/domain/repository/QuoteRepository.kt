package com.vikravch.sampleapp.simple_feature.domain.repository

interface QuoteRepository {
    suspend fun getQuote(): Result<String>
}