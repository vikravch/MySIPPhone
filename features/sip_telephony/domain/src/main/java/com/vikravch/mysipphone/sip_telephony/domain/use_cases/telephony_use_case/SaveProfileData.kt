package com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case

import com.vikravch.mysipphone.sip_telephony.domain.repository.SipConnectionRepository

class SaveProfileData(
    private val repository: SipConnectionRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        domain: String
    ): Result<Boolean> {
        repository.register(username, password, domain)
        return Result.success(true)
    }
}
