package com.vikravch.mysipphone.trash

import android.util.Log
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketTimeoutException

class SipManagerSocketImpl : SipManager {
    private val localIp: String = "192.168.1.100" // Replace with dynamic local IP
    private val localPort: Int = 5060
    private val nonce = System.currentTimeMillis().toString()

    override fun register(username: String, password: String, domain: String): String {
        val sipMessage = """
        REGISTER sip:$domain SIP/2.0
        Via: SIP/2.0/UDP $localIp:$localPort;branch=z9hG4bK-${nonce}
        Max-Forwards: 70
        To: <sip:$username@$domain>
        From: <sip:$username@$domain>;tag=${nonce}
        Call-ID: ${nonce}@$localIp
        CSeq: 1 REGISTER
        Contact: <sip:$username@$localIp:$localPort>
        Content-Length: 0
    """.trimIndent()
        Log.d("SipManagerSocketImpl", "Sending SIP message: $sipMessage")

        return sendSipMessage(domain, 5060, sipMessage)
    }

    override fun sendMessages(username: String, recipient: String, domain: String, messageBody: String): Boolean {
        val sipMessage = """
        MESSAGE sip:$recipient@$domain SIP/2.0
        Via: SIP/2.0/UDP $localIp:$localPort;branch=z9hG4bK-${nonce}
        Max-Forwards: 70
        To: <sip:$recipient@$domain>
        From: <sip:$username@$domain>;tag=${nonce}
        Call-ID: ${System.currentTimeMillis()}@$localIp
        CSeq: 1 MESSAGE
        Content-Type: text/plain
        Content-Length: ${messageBody.length}
        
        $messageBody
    """.trimIndent()
        Log.d("SipManagerSocketImpl", "Sending SIP message: $sipMessage")

        val response = sendSipMessage(domain, 5060, sipMessage)
        return response.contains("200 OK")
    }

    override fun unregister(username: String, domain: String, password: String, nonce: String): Boolean {
        val sipMessage = """
        REGISTER sip:$domain SIP/2.0
        Via: SIP/2.0/UDP $localIp:$localPort;branch=z9hG4bK-${nonce}
        Max-Forwards: 70
        To: <sip:$username@$domain>
        From: <sip:$username@$domain>;tag=${nonce}
        Call-ID: ${nonce}@$localIp
        CSeq: 2 REGISTER
        Contact: <sip:$username@$localIp:$localPort>;expires=0
        Content-Length: 0
    """.trimIndent()
        Log.d("SipManagerSocketImpl", "Sending SIP message: $sipMessage")

        val response = sendSipMessage(domain, 5060, sipMessage)
        return response.contains("200 OK")
    }

    private fun sendSipMessage(host: String, port: Int, message: String): String {
        val socket = DatagramSocket()
        val address = InetAddress.getByName(host)
        val packet = DatagramPacket(message.toByteArray(), message.length, address, port)

        // Send the SIP message
        socket.send(packet)

        // Receive the response
        val buffer = ByteArray(4096)
        val responsePacket = DatagramPacket(buffer, buffer.size)
        socket.soTimeout = 30000 // Timeout for response
        return try {
            socket.receive(responsePacket)
            String(responsePacket.data, 0, responsePacket.length)
        } catch (e: SocketTimeoutException) {
            "No response received"
        } finally {
            socket.close()
        }
    }
}

fun generateDigest(username: String, realm: String, password: String, uri: String, nonce: String): String {
    val ha1 = md5("$username:$realm:$password")
    val ha2 = md5("REGISTER:$uri")
    return md5("$ha1:$nonce:$ha2")
}