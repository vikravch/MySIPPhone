package com.vikravch.mysipphone.trash

import android.content.Context
import org.linphone.core.Core
import org.linphone.core.Factory

class LinphoneSipManager(context: Context): SipManager {
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
        username: String,
        recipient: String,
        domain: String,
        messageBody: String
    ): Boolean {
        val address = Factory.instance().createAddress("sip:$recipient")

        if (address!==null) {
            val chatRoom = core.createChatRoom(address)
            val message = chatRoom?.createEmptyMessage()
            message?.addUtf8TextContent("Hello Linphone")
            message?.send()
        }

        println("Message sent to $recipient")
        return true
    }

    override fun unregister(
        username: String,
        domain: String,
        password: String,
        nonce: String
    ): Boolean {
        core.stop()
        return true
    }

}