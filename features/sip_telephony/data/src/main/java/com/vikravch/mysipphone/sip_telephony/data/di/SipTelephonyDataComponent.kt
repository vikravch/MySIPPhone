package com.vikravch.mysipphone.sip_telephony.data.di

import android.content.Context
import com.vikravch.mysipphone.sip_telephony.data.repository.LinphoneSipConnectionRepository
import com.vikravch.mysipphone.sip_telephony.domain.repository.SipConnectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SipTelephonyDataComponent {

    @Provides
    @Singleton
    fun provideLinphoneSipConnectionRepository(
        @ApplicationContext context: Context
    ): SipConnectionRepository {
        return LinphoneSipConnectionRepository(context)
    }

}
