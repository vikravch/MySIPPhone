package com.vikravch.sampleapp.ktor_network

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.*

@Module
@InstallIn(SingletonComponent::class)
object KtorServiceComponent {

    @Provides
    @Singleton
    fun provideKtorService(): HttpClient {
        return HttpClient(Android) {
           /* install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }*/

            /*engine {
                connectTimeout = 10000
                socketTimeout = 10000
            }*/
            /*install(Logging) {
                                logger = object : Logger {
                                    override fun log(message: String) {
                                        Log.v("http log: ", message)
                                    }
                                }
                                level = LogLevel.ALL
                            }*/


               /* install(DefaultRequest) {
                    header(HttpHeaders.ContentType, Json)
                    url {
                        protocol = URLProtocol("http", 80)
                        host = "api.kanye.rest"
                    }
                }*/
            }

    }
}