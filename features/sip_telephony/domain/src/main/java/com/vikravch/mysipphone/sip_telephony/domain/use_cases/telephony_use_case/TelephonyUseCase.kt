    package com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case

    data class TelephonyUseCase(
        val sendMessage: SendMessage,
        val saveProfileData: SaveProfileData,
        val closeProfile: CloseProfile,
        val getProfileData: GetProfileData
    )