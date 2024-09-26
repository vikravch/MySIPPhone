package com.vikravch.sampleapp.simple_feature.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.vikravch.sampleapp.simple_feature.data.database.dao.UserDao
import com.vikravch.sampleapp.simple_feature.data.remote.retrofit.QuoteApi
import com.vikravch.sampleapp.simple_feature.data.repository.QuoteNetworkRetrofitRepository
import com.vikravch.sampleapp.simple_feature.data.repository.UserFirestoreRepository
import com.vikravch.sampleapp.simple_feature.data.repository.UserRoomRepository
import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SimpleFeatureDataComponent {

    @Provides
    @Singleton
    fun provideQuoteApi(
        retrofit: Retrofit
    ): QuoteApi {
        return retrofit.create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun providesQuoteRepository(
        quoteApi: QuoteApi
    ): QuoteRepository {
        return QuoteNetworkRetrofitRepository(quoteApi)
    }

    /*@Provides
    @Singleton
    fun providesQuoteRepository(
        httpClient: HttpClient
    ): QuoteRepository{
        return QuoteNetworkKtorRepository(httpClient)
    }*/

    /*@Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRoomRepository(userDao)
    }*/

    @Provides
    @Singleton
    fun provideFirestoreRepository(
        firestore: FirebaseFirestore
    ): UserRepository {
        return UserFirestoreRepository(firestore)
    }
}