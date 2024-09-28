package com.vikravch.sampleapp.simple_feature.data.fake

import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository

class QuoteFakeRepository: QuoteRepository {

    override suspend fun getQuote(): Result<String> {
        return Result.success("This is a fake quote")
    }
}