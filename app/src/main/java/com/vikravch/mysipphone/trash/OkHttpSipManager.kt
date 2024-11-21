package com.vikravch.mysipphone.trash

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.InetAddress

import java.net.NetworkInterface
import java.security.MessageDigest

fun getLocalIpAddress(): String? {
    try {
        val interfaces = NetworkInterface.getNetworkInterfaces()
        for (networkInterface in interfaces) {
            val addresses = networkInterface.inetAddresses
            for (address in addresses) {
                if (!address.isLoopbackAddress && address is InetAddress) {
                    val ipAddress = address.hostAddress
                   // if (ipAddress.contains(":")) continue // Skip IPv6 addresses
                    return ipAddress
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5") // Get an instance of the MD5 digest
    val digest = md.digest(input.toByteArray()) // Calculate the digest for the input string
    // Convert the digest (byte array) into a hexadecimal string
    return digest.joinToString("") { "%02x".format(it) }
}

fun calculateResponse(username: String, password: String, domain: String, nonce: String, method: String, uri: String): String {
    // Here you would calculate the MD5 hash using the formula for digest authentication
    val ha1 = md5("$username:$domain:$password")  // First hash (HA1)
    val ha2 = md5("$method:$uri")                // Second hash (HA2)
    return md5("$ha1:$nonce:$ha2")               // Final response
}
class OkHttpSipManager: SipManager {
    private val localIp = getLocalIpAddress() ?: run {
        Log.e("SIP", "Failed to get local IP address")
    }
    private val localPort = 5061
    private val client = OkHttpClient()
    private val nonce = System.currentTimeMillis().toString()

    override fun register(username: String, password: String, domain: String): String {
        val registerRequest = """
        REGISTER sip:$domain SIP/2.0
        Via: SIP/2.0/UDP $localIp:$localPort;branch=z9hG4bK-${nonce}
        Max-Forwards: 70
        To: <sip:$username@$domain>
        From: <sip:$username@$domain>;tag=${nonce}
        Call-ID: ${nonce}@$localIp
        CSeq: 1 REGISTER
        Contact: <sip:$username@$localIp:$localPort>
        Authorization: Digest username="$username", realm="$domain", nonce="$nonce", uri="sip:$domain", response=""
        Content-Length: 0
    """.trimIndent()

        val request = Request.Builder()
            .url("http://$domain") // Adjust as needed for SIP server resolution
            .method("REGISTER", registerRequest.toRequestBody())
            .build()

        Log.d("SIP", "Registering: $registerRequest")

        val response = client.newCall(request).execute()

        return if (response.isSuccessful) {
            Log.d("SIP", "Registered successfully: ${response.code}")
            ""
        } else {
            Log.e("SIP", "Registration failed: ${response.code}")
            ""
        }
    }

    override fun sendMessages(
        username: String,
        recipient: String,
        domain: String,
        messageBody: String
    ): Boolean {

        val localIp = getLocalIpAddress() ?: run {
            Log.e("SIP", "Failed to get local IP address")
            return false
        }
        val callId = "${nonce}@$localIp" // Unique Call-ID
        val cSeq = 1 // Increment with every request (mandatory for SIP)

        // Create the SIP MESSAGE request
        val sipMessage = """
        MESSAGE $recipient SIP/2.0
        Via: SIP/2.0/UDP $localIp:$localPort;branch=z9hG4bK-${nonce}
        Max-Forwards: 70
        From: <sip:$username@$domain>;tag=${nonce}
        To: <$recipient>
        Call-ID: $callId
        CSeq: $cSeq MESSAGE
        Content-Type: text/plain
        Content-Length: ${messageBody.length}
        Authorization: Digest username="$username", realm="$domain", nonce="$nonce", uri="sip:$domain", response=""
       
        $messageBody
    """.trimIndent()

        val request = Request.Builder()
            .url("http://$domain") // Replace with the SIP server's URL or IP
            .method("POST", sipMessage.toRequestBody()) // Use the appropriate SIP server endpoint
            .build()

        Log.d("SIP", "Sending SIP message: $sipMessage")

        return try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                println("SIP Message sent successfully: ${response.code} $messageBody")
                true
            } else {
                println("Failed to send SIP message: ${response.code}")
                false
            }
        } catch (e: Exception) {
            println("Error sending SIP message: ${e.message}")
            false
        }
    }



    override fun unregister(username: String, domain: String, password: String, nonce: String): Boolean {
        // Define the local IP and port
        val localIp = getLocalIpAddress() //"10.0.2.15" // Replace with actual local IP
        //val localPort = 5060      // Default SIP port (UDP or TCP)

        // Calculate the response (same process as when registering, using the correct nonce)
        val response = calculateResponse(username, password, domain, nonce, "UNREGISTER", "sip:$domain")

        // Construct the SIP UNREGISTER request
        val unregisterRequest = """
        UNREGISTER sip:$domain SIP/2.0
        Via: SIP/2.0/UDP $localIp:$localPort;branch=z9hG4bK-${nonce}
        Max-Forwards: 70
        To: <sip:$username@$domain>
        From: <sip:$username@$domain>;tag=${nonce}
        Call-ID: ${nonce}@$localIp
        CSeq: 1 UNREGISTER
        Contact: <sip:$username@$localIp:$localPort>
        Authorization: Digest username="$username", realm="$domain", nonce="$nonce", uri="sip:$domain", response="$response"
        Content-Length: 0
    """.trimIndent()

        // Send the UNREGISTER request using OkHttp
        val request = Request.Builder()
            .url("http://$domain")  // SIP server URL
            .method("UNREGISTER", unregisterRequest.toRequestBody("text/plain".toMediaTypeOrNull()))
            .build()

        val responseSIP = client.newCall(request).execute()

        return if (responseSIP.isSuccessful) {
            println("Unregistered successfully: ${responseSIP.code}")
            true
        } else {
            println("Unregistration failed: ${responseSIP.code}")
            false
        }
    }
}