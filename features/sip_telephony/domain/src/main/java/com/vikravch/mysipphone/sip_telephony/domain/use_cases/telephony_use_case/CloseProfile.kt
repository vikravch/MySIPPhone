package com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case

import com.vikravch.mysipphone.sip_telephony.domain.repository.SipConnectionRepository

class CloseProfile(
    private val repository: SipConnectionRepository
) {
    operator fun invoke(
        username: String,
        password: String,
        domain: String
    ): Result<Boolean> {
        repository.unregister(username, domain, password, "nonce")
        return Result.success(true)
    }
}
