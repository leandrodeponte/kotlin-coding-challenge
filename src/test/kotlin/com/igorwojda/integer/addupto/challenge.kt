package com.igorwojda.integer.addupto

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun addUpTo(n: Int): Int {
    return (1..n).reduce { n,  m -> n + m }
}

private fun addUpTo2(n: Int): Int {
    return (1..n).sum()
}

private fun addUpTo3(n: Int): Int {
   return List(n + 1) { it }.sum()
}


private class Test {
    @Test
    fun `add up to 1`() {
        addUpTo(1) shouldBeEqualTo 1
    }

    @Test
    fun `add up to 3`() {
        addUpTo(3) shouldBeEqualTo 6
    }

    @Test
    fun `add up to 10`() {
        addUpTo(10) shouldBeEqualTo 55
    }

    @Test
    fun `add2 up to 3`() {
        addUpTo2(3) shouldBeEqualTo 6
    }

    @Test
    fun `add2 up to 102`() {
        addUpTo2(10) shouldBeEqualTo 55
    }

    @Test
    fun `add3 up to 3`() {
        addUpTo3(3) shouldBeEqualTo 6
    }

    @Test
    fun `add3 up to 102`() {
        addUpTo3(10) shouldBeEqualTo 55
    }
}
