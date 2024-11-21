package com.vikravch.mysipphone.sip_telephony.presentation.page.message

sealed class MessageTabEvent {
    data class SendMessage(val recipient: String, val message: String) : MessageTabEvent()
}