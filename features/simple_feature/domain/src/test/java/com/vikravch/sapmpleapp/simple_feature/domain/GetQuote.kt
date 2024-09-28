package com.vikravch.sapmpleapp.simple_feature.domain

import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository
import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.GetQuote
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetQuote {


    private val repository = mockk<QuoteRepository>()
    private val getQuote = GetQuote(repository)

    @Test
    fun `login with valid inputs`() = runBlocking {
        // given
        val email = "test"
        val password = "test"
        coEvery { repository.getQuote() } returns Result.success("")

        getQuote()

        coVerify { repository.getQuote() }
    }
}