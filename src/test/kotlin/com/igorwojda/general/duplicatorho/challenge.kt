package com.igorwojda.general.duplicatorho

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class SquareService {
    private val duplicator = Duplicator()
    fun requestWithDuplication(value: Int) = duplicator.duplicate(value, ::requestToService)
    fun requestToService(s: Int) = s*s
}

class Duplicator {
    fun duplicate(s: Int, request: (Int) -> Int = { 0 }) = 2 * request(s)
}

private class Test {

    @Test
    fun `Do not duplicate result`() {
        val client = SquareService()
         client.requestToService(2) shouldBeEqualTo 4
    }

    @Test
    fun `Duplicate result`() {
        val client = SquareService()
        client.requestWithDuplication(2) shouldBeEqualTo 8
    }

}