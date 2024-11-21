package com.vikravch.mysipphone.sip_telephony.domain.repository

interface SipConnectionRepository {
    fun register(username: String, password: String, domain: String): String
    fun sendMessages( username: String,
                      recipient: String,
                      domain: String,
                      messageBody: String): Boolean
    fun unregister(username: String, domain: String, password: String, nonce: String): Boolean
    fun getProfileData(): String
}