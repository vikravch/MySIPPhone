package com.vikravch.features.simple_feature.data

import com.vikravch.sampleapp.core.NetworkInfo
import com.vikravch.sampleapp.simple_feature.data.remote.retrofit.QuoteApi
import com.vikravch.sampleapp.simple_feature.data.repository.QuoteNetworkRetrofitRepository
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class SimpleFeatureDataTest {

    private lateinit var quoteRepository: QuoteNetworkRetrofitRepository
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var networkInfo: NetworkInfo
    private lateinit var api: QuoteApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(QuoteApi::class.java)
        networkInfo = mockk(relaxed = true)
        quoteRepository = QuoteNetworkRetrofitRepository(api, networkInfo)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test getQuote success`() = runBlocking{
        every { networkInfo.isConnected() } returns true

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(quoteResponse)
        )

        val result = quoteRepository.getQuote()

        assertThat(result.isSuccess).isTrue()
        assertThat(result).isEqualTo(Result.success("Style is genderless"))
    }
}