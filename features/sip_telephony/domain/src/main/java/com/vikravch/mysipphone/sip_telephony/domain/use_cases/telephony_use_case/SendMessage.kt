package com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case

import com.vikravch.mysipphone.sip_telephony.domain.repository.SipConnectionRepository

class SendMessage(
    private val repository: SipConnectionRepository
) {
    suspend operator fun invoke(
        username: String,
        recipient: String,
        domain: String,
        messageBody: String
    ): Result<Boolean> {
        repository.sendMessages(
            username,
            recipient,
            domain,
            messageBody
        )
        return Result.success(true)
    }
}
