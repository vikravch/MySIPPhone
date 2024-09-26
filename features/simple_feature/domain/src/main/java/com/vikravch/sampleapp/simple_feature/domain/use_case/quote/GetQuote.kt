package com.vikravch.sampleapp.simple_feature.domain.use_case.quote

import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository

class GetQuote(
    private val quoteRepository: QuoteRepository
) {
    suspend operator fun invoke(): Result<String> {
        return quoteRepository.getQuote()
    }
}