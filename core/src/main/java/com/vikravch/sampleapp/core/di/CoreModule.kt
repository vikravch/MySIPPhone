package com.vikravch.sampleapp.core.di

import android.content.Context
import android.content.SharedPreferences
import com.vikravch.sampleapp.core.NetworkInfo
import com.vikravch.sampleapp.core.NetworkInfoImpl
import com.vikravch.sampleapp.core.preference.PreferencesRepository
import com.vikravch.sampleapp.core.preference.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideNetworkInfo(@ApplicationContext context: Context): NetworkInfo {
        return NetworkInfoImpl(context)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): PreferencesRepository {
        return SharedPreferencesRepository(sharedPreferences)
    }
}