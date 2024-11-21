package com.vikravch.mysipphone.sip_telephony.domain.di

import com.vikravch.mysipphone.sip_telephony.domain.repository.SipConnectionRepository
import com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case.CloseProfile
import com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case.GetProfileData
import com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case.SaveProfileData
import com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case.SendMessage
import com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case.TelephonyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SipTelephonyDomainComponent {

    @Provides
    @Singleton
    fun provideTelephonyUseCase(
        sipConnectionRepository: SipConnectionRepository
    ): TelephonyUseCase {
        return TelephonyUseCase(
            sendMessage = SendMessage(sipConnectionRepository),
            saveProfileData = SaveProfileData(sipConnectionRepository),
            closeProfile = CloseProfile(sipConnectionRepository),
            getProfileData = GetProfileData(sipConnectionRepository)
        )
    }
    
}