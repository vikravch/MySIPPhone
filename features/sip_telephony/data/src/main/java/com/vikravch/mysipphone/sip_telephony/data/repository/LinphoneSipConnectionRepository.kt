package com.vikravch.mysipphone.sip_telephony.data.repository

import android.content.Context
import com.vikravch.mysipphone.sip_telephony.domain.repository.SipConnectionRepository
import org.linphone.core.Core
import org.linphone.core.Factory

class LinphoneSipConnectionRepository(context: Context): SipConnectionRepository {
    private val core: Core

    init {
        Factory.instance().setDebugMode(true, "Linphone")
        core = Factory.instance().createCore(null, null, context)
        core.start()
    }

    override fun register(username: String, password: String, domain: String): String {
        val authInfo = Factory.instance().createAuthInfo(username, null, password, null, null, domain)
        core.addAuthInfo(authInfo)
        println("SIP account registered.")
        return "nonce"
    }

    override fun sendMessages(
        username: String, // unused
        recipient: String,
        domain: String, // unused
        messageBody: String
    ): Boolean {
        val address = Factory.instance().createAddress("sip:$recipient")

        if (address!==null) {
            val chatRoom = core.createChatRoom(address)
            val message = chatRoom?.createEmptyMessage()
            message?.addUtf8TextContent(messageBody)
            message?.send()
        }

        println("Message sent to $recipient")
        return true
    }

    override fun unregister(
        username: String, // unused
        domain: String, // unused
        password: String, // unused
        nonce: String // unused
    ): Boolean {
        core.stop()
        return true
    }

    override fun getProfileData(): String {
        return  if(core.authInfoList.isNotEmpty()) {
            "${core.authInfoList[0].username}@${core.authInfoList[0].domain}"
        } else {
            ""
        }
    }

}