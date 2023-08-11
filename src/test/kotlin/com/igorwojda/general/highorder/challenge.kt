package com.igorwojda.general.highorder

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PaymentApiClient : ApiClient {
    private val requester = ResilientRequester()
    override fun request(str: String?) {
        requester.requestWithResilience(str, ::requestToService)
    }

    private fun requestToService(s: String?) {
        s?.let { println("Requesting with param: $s ") }
    }
}

class SendingApiClient : ApiClient {
    val requester = ResilientRequester()
    override fun request(s: String?) {
        requester.requestWithResilience()
    }
}

interface ApiClient {
    fun request(str: String?)
}

class ResilientRequester {
    fun requestWithResilience(s: String? = null, request: (String?) -> Unit = { println("No function sent") }) {
        println("Applying resilience")
        request(s)
        println("Requested with resilience")
    }
}

private class Test {

    @Test
    fun `sum 1, 2 and 3`() {
        val client = PaymentApiClient()
        assertDoesNotThrow {
            client.request("x")
        }
    }
    

}