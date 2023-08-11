package com.igorwojda.general.reactiveflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.random.Random

private fun retrieve()  =
    flowOf(0,1,2,3,4,5,6,7,8,9)

private fun retrieveRandom() = flow {
    (0..9).forEach {
        Random.nextLong(from = 100, until = 500).let { timeInMillis ->
            println("Waiting for $timeInMillis milliseconds")
            delay(timeInMillis)
        }
        emit(it)
    }
}

private class Test {

    init {
        println("Initialized")
    }

    @Test
    fun `should retrieve all items`() {
        val list = mutableListOf<Int>()
        runBlocking {
            retrieveRandom().collect {
                list.add(it)
            }
        }
        list.size shouldBeEqualTo 10
    }

    @Test
    fun `should retrieve all items2`() {
        val list = mutableListOf<Int>()
        runBlocking {
            retrieve().collect {
                list.add(it)
            }
        }
        list.size shouldBeEqualTo 10
    }

}