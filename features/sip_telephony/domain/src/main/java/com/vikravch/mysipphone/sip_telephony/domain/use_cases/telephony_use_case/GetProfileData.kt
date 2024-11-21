package com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case

import com.vikravch.mysipphone.sip_telephony.domain.repository.SipConnectionRepository

class GetProfileData(
    private val repository: SipConnectionRepository
) {
    operator fun invoke(): String{
        return repository.getProfileData()
    }
}