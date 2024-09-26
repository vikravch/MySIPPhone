package com.vikravch.sampleapp.simple_feature.domain.di

import com.vikravch.sampleapp.simple_feature.domain.repository.QuoteRepository
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository
import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.GetQuote
import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.QuoteUseCases
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.AddUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.DeleteUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.GetAllUsers
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.GetUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.UpdateUser
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SimpleFeatureDomainComponent {

    @Provides
    @Singleton
    fun provideQuoteUseCases(
        quoteRepository: QuoteRepository
    ): QuoteUseCases {
        return QuoteUseCases(
            getQuote = GetQuote(quoteRepository)
        )
    }

    @Provides
    @Singleton
    fun provideUserUseCases(
        userRepository: UserRepository
    ): UsersUseCases {
        return UsersUseCases(
            getUser = GetUser(userRepository),
            getAllUsers = GetAllUsers(userRepository),
            addUser = AddUser(userRepository),
            deleteUser = DeleteUser(userRepository),
            updateUser = UpdateUser(userRepository)
        )
    }
}