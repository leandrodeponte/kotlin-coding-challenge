package com.igorwojda.general.aggfunctions

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeLessThan
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.reflect.InvocationTargetException

private fun separateByMod(numbers: List<Int>, param: Int)
        = numbers.partitionSelfMade { customMod(it, param) }
private fun sum(numbers: List<Int>) = numbers.sum()
private fun sum(a : Int, b : Int) = a + b
private fun multiply(a : Int, b : Int) = a * b
private fun divide(a : Int, b : Int) = require(b > 0) { "The divisor should be greater than zero" }
    .let { a.div(b) }

private fun customMod(value : Int, param : Int) = (value % param == 0)

private fun reduce(numbers: List<Int>, function: (Int, Int) -> Int)
= numbers.reduce {n, m -> function(n, m) }

private class Test {

    @Test
    fun `sum 1, 2 and 3`() {
        sum(listOf(1,2,3)) shouldBeEqualTo 6
    }

    @Test
    fun `reduce with sum 1, 2 and 3`() {
        reduce(listOf(1,2,3,4), ::sum) shouldBeEqualTo 10
    }

    @Test
    fun `reduce with multiplication 1, 2 and 3`() {
        reduce(listOf(1,2,3,4), ::multiply) shouldBeEqualTo 24
    }

    @Test
    fun `reduce with division 1, 2, 3 and 4`() {
        reduce(listOf(1,2,3,4), ::divide) shouldBeLessThan  1
    }

    @Test
    fun `reduce with division containing 0 should throw error`() {
        assertThrows<IllegalArgumentException> { reduce( listOf(1, 2, 0, 4), ::divide) }
    }

    @Test
    fun `separate 1, 2 and 3 even and odd`() {
        separateByMod(listOf(1,2,3), 2).let {
            it.first.size shouldBeEqualTo 1
            it.second.size shouldBeEqualTo 2
        }
    }

    @Test
    fun `param is true`() {
        assert(true)
    }

    private fun assert(value : Boolean? = null) {
        assertDoesNotThrow {
            requireNotNull(value ) { "the value should not be null." }
                .also { assertTrue(value) }
        }
    }



}

private fun List<Int>.partitionSelfMade(criteria: (Int) -> Boolean) : Pair<List<Int>, List<Int>>  {
    var trueList = mutableListOf<Int>()
    var elseList = mutableListOf<Int>()
    this.forEach { element ->
        when {
            criteria(element) -> trueList.add(element)
            else -> elseList.add(element)
        }
    }
    return Pair(trueList, elseList)
}